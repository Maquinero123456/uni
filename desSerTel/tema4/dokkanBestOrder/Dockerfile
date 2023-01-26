FROM public.ecr.aws/lambda/python:3.9
COPY . .

RUN pip install -r requirements.txt
CMD ["main.handler"]
