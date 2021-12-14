import ssl
from pprint import pprint
import time
import requests

ssl._create_default_https_context = ssl._create_unverified_context
access_token = 'Lxpx3qJkurw1xRzmSRabOA))'
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


# intitle = 'how to use java lamada expression'
# req = requests.get(
#             'https://api.stackexchange.com/2.3/search?order=desc&sort=activity&intitle=' + intitle + '&site=stackoverflow', header)
# req = req.json()
# nodes = list(req['items'])
# edges = []
# questions = list(req['items'])
# all_nodes = list(req['items'])  # 防止死循环
# while len(questions) > 0:
#     nums = len(questions)
#     for i in range(nums):
#         question = questions[i]
#         answers = get_answers(str(question['question_id']))
#         for answer in answers:
#             nodes.append(answer)
#             edges.append({'relation': 'answer', 'source': question['question_id'], 'target': answer['answer_id']})
#         time.sleep(0.2)
#         related = get_related(str(question['question_id']))
#         for r in related:
#             if not all_nodes.__contains__(r['question_id']):
#                 all_nodes.append(r)
#                 nodes.append(r)
#                 questions.append(r)
#                 edges.append({'relation': 'related', 'source': question['question_id'], 'target': r['question_id']})
#         time.sleep(0.2)
#         links = get_links(str(question['question_id']))
#         for link in links:
#             if not all_nodes.__contains__(link['question_id']):
#                 all_nodes.append(link)
#                 nodes.append(link)
#                 questions.append(link)
#                 edges.append(
#                     {'relation': 'related', 'source': question['question_id'], 'target': link['question_id']})
#         time.sleep(0.2)
#     questions = questions[nums:]
#     pprint(nodes)
#     nodes.clear()
#     edges.clear()


import json

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


@app.route('/api/chart/test')
def test():
    def generate():
        time.sleep(3)
        yield "1"
        time.sleep(10)
        yield json.dumps({"node": [1, 2, 3, 4]})
        time.sleep(10)
        yield "3"

    return Response(stream_with_context(generate()))


@app.route('/api/chart/search/<input>')
def search(input):
    def generate(inputs):
        intitle = inputs
        count = 1
        req = requests.get(
            'https://api.stackexchange.com/2.3/search?order=desc&sort=activity&intitle=' + intitle + '&site=stackoverflow',
            header)
        req = req.json()
        nodes = list(req['items'])
        edges = []
        yield json.dumps({"nodes": nodes, "edges": edges})
        questions = list(req['items'])
        all_nodes = list(req['items'])  # 防止死循环
        all_edges = []
        while len(questions) > 0:
            nums = len(questions)
            for i in range(nums):
                question = questions[i]
                answers = get_answers(str(question['question_id']))
                count += 1
                for answer in answers:
                    all_nodes.append({'node_name': 'a_' + str(answer['answer_id']), 'node_group': 'answer', 'node_content': answer, 'classes': 'node'})
                    nodes.append({'node_name': 'a_' + str(answer['answer_id']), 'node_group': 'answer', 'node_content': answer, 'classes': 'node'})
                    edges.append(
                        {'edge_name': str(question['question_id'])+'->' + str(answer['answer_id']), 'edge_group': 'answer',
                         'source': 'q_'+str(question['question_id']), 'target': 'a_'+str(answer['answer_id']), 'classes': 'edge'})
                    all_edges.append(
                        {'edge_name': str(question['question_id'])+'->' + str(answer['answer_id']), 'edge_group': 'answer',
                         'source': 'q_'+str(question['question_id']), 'target': 'a_'+str(answer['answer_id']), 'classes': 'edge'})

                time.sleep(0.2)
                related = get_related(str(question['question_id']))
                count += 1
                for r in related:
                    if not all_nodes.__contains__(r['question_id']):
                        all_nodes.append({'node_name': 'q_' + str(r['question_id']), 'node_group': 'question',
                                          'node_content': r, 'classes': 'node'})
                        nodes.append({'node_name': 'q_' + str(r['question_id']), 'node_group': 'question',
                                      'node_content': r, 'classes': 'node'})
                        questions.append(r)
                        edges.append(
                            {'edge_name': str(question['question_id'])+'->'+str(r['question_id']), 'edge_group': 'related', 'source': 'q_'+str(question['question_id']), 'target': 'q_'+str(r['question_id']), 'classes': 'edge'})
                        all_edges.append(
                            {'edge_name': str(question['question_id'])+'->'+str(r['question_id']), 'edge_group': 'related', 'source': 'q_'+str(question['question_id']), 'target': 'q_'+str(r['question_id']), 'classes': 'edge'})
                time.sleep(0.2)
                links = get_links(str(question['question_id']))
                count += 1
                for link in links:
                    if not all_nodes.__contains__(link['question_id']):
                        all_nodes.append({'node_name': 'q_' + str(link['question_id']), 'node_group': 'question',
                                          'node_content': link, 'classes': 'node'})
                        nodes.append({'node_name': 'q_' + str(link['question_id']), 'node_group': 'question',
                                      'node_content': link, 'classes': 'node'})
                        questions.append(link)
                        edges.append(
                            {'edge_name': str(question['question_id'])+'->'+str(link['question_id']), 'edge_group': 'link', 'source': 'q_'+str(question['question_id']), 'target': 'q_'+str(link['question_id']), 'classes': 'edge'})
                        all_edges.append(
                            {'edge_name': str(question['question_id'])+'->'+str(link['question_id']), 'edge_group': 'link', 'source': 'q_'+str(question['question_id']), 'target': 'q_'+str(link['question_id']), 'classes': 'edge'})
                time.sleep(0.2)
                if count >= 30:
                    pprint("exit")
                    #pprint(all_edges)
                    yield json.dumps({"nodes": all_nodes, "edges": all_edges, "end": True})
                    return
            questions = questions[nums:]
            #pprint(edges)
            yield json.dumps({"nodes": all_nodes, "edges": all_edges, "end": False})
            nodes.clear()
            edges.clear()

    return Response(stream_with_context(generate(input)))


if __name__ == '__main__':
    app.run()
