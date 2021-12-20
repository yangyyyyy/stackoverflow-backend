import ssl
from pprint import pprint
import time
import requests

ssl._create_default_https_context = ssl._create_unverified_context
access_token = 'vo9jCrrQZX4H9K4id9PWtA))'
header = {'Authorization': access_token}


def get_answers(question_id):
    t_request = requests.get(
        'https://api.stackexchange.com/2.3/questions/' + question_id + '/answers?pagesize=100&order=desc&sort=activity&site=stackoverflow',
        header)
    t_answers = t_request.json()['items']
    return t_answers


def get_related(question_id):
    request = requests.get(
        'https://api.stackexchange.com/2.3/questions/' + question_id + '/related?pagesize=100&order=desc&sort=activity&site=stackoverflow',
        header)
    relate = request.json()['items']
    return relate


def get_links(question_id):
    request = requests.get(
        'https://api.stackexchange.com/2.3/questions/' + question_id + '/linked?pagesize=100&order=desc&sort=activity&site=stackoverflow',
        header)
    linked = request.json()['items']
    return linked


import json
from bs4 import BeautifulSoup
from flask import Flask, Response, stream_with_context
from flask_cors import cross_origin

app = Flask(__name__)


@app.route("/")
@cross_origin(supports_credentials=True)
def index():
    result = {
        'status': "200",
        'data': 'Hello, world!',
    }
    return json.dumps(result)


@app.route('/pyapi/chart/faq/<tag>')
@cross_origin(supports_credentials=True)
def faq(tag):
    req = requests.get(
        'https://api.stackexchange.com/2.3/tags/'+tag+'/faq?site=stackoverflow',
        header)
    req = req.json()
    questions = list(req['items'])
    for i in range(len(questions)):
        questions[i]["question_id"] = "q_" + str(questions[i]["question_id"])
    return json.dumps({"results": questions})

@app.route('/pyapi/chart/qcontent/<id>')
@cross_origin(supports_credentials=True)
def qcontent(id):
    url = "https://stackoverflow.com/q/"+id
    content=requests.get(url).content
    soup = BeautifulSoup(content)
    soup = soup.find('div',"question")
    content = soup.find('div','s-prose js-post-body')
    #print(content)
    return str(content)

@app.route('/pyapi/chart/content/<id>')
@cross_origin(supports_credentials=True)
def content(id):
    url = "https://stackoverflow.com/a/"+id
    content=requests.get(url).content
    soup = BeautifulSoup(content)
    soup = soup.find(id='answer-'+str(id))
    content = soup.find('div','s-prose js-post-body')
    #print(content)
    return str(content)

@app.route('/pyapi/chart/search/<input>')
@cross_origin(supports_credentials=True)
def search(input):
    intitle = input
    req = requests.get(
        'https://api.stackexchange.com/2.3/search?order=desc&sort=activity&intitle=' + intitle + '&site=stackoverflow',
        header)
    req = req.json()
    nodes = list(req['items'])
    for i in range(len(nodes)):
        nodes[i]["question_id"] = "q_"+str(nodes[i]["question_id"])
    return json.dumps({"results": nodes})

@app.route('/pyapi/chart/scratch/<question_id>')
@cross_origin(supports_credentials=True)
def scratch(question_id):
    nodes = []
    edges = []
    question_id = str(question_id.split("_")[1])
    answers = get_answers(question_id)
    for answer in answers:
        nodes.append({'node_name': 'a_' + str(answer['answer_id']), 'node_group': 'answer', 'node_content': answer, 'classes': 'node'})
        edges.append(
            {'edge_name': question_id+'->' + str(answer['answer_id']), 'edge_group': 'answer',
             'source': 'q_'+question_id, 'target': 'a_'+str(answer['answer_id']), 'classes': 'edge'})
    time.sleep(0.2)

    related = get_related(question_id)
    for r in related:
        nodes.append({'node_name': 'q_' + str(r['question_id']), 'node_group': 'question',
                                      'node_content': r, 'classes': 'node'})
        edges.append(
            {'edge_name': question_id+'->'+str(r['question_id']), 'edge_group': 'related', 'source': 'q_'+question_id, 'target': 'q_'+str(r['question_id']), 'classes': 'edge'})
    time.sleep(0.2)

    links = get_links(question_id)
    for link in links:
        nodes.append({'node_name': 'q_' + str(link['question_id']), 'node_group': 'question',
                             'node_content': link, 'classes': 'node'})
        edges.append(
            {'edge_name': question_id+'->'+str(link['question_id']), 'edge_group': 'link',
            'source': 'q_'+question_id, 'target': 'q_'+str(link['question_id']), 'classes': 'edge'})
    time.sleep(0.2)
    return json.dumps({"nodes": nodes, "edges": edges})



if __name__ == '__main__':
    app.run()
