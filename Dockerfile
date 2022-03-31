FROM python:3.6.4

RUN mkdir /app
RUN pip install -U requests
RUN pip install -U flask_cors
Run pip install -U bs4

copy scratch/scratch.py /app

WORKDIR /app

CMD python scratch.py