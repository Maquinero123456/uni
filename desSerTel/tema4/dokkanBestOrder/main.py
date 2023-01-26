from http.client import HTTPResponse
from fastapi import FastAPI, Request, Form
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from characterSearch import characterSearch
from fastapi.responses import RedirectResponse
from fastapi.responses import HTMLResponse
from teamOptimizer import teamOptimizer
import os
import json
from mangum import Mangum

app = FastAPI()
app.mount("/static", StaticFiles(directory="static"), name="static")
templates = Jinja2Templates(directory="templates")

@app.get("/")
async def root():
    response = RedirectResponse(url='./home')
    return response

@app.get("/home", response_class=HTTPResponse)
async def home(request : Request):
    return templates.TemplateResponse("index.html", {"request":request})

@app.post("/team", response_class=HTMLResponse)
async def team(request : Request, leader : int = Form(), char2 : int = Form(), char3 : int = Form(), char4: int = Form(), char5 : int = Form(), char6 : int = Form(), friend : int = Form()):
    if(friend==0):
        friend=leader

    personajes = []
    search = characterSearch()

    personajes.append(search.buscarPersonaje(leader))
    personajes.append(search.buscarPersonaje(char2))
    personajes.append(search.buscarPersonaje(char3))
    personajes.append(search.buscarPersonaje(char4))
    personajes.append(search.buscarPersonaje(char5))
    personajes.append(search.buscarPersonaje(char6))
    personajes.append(search.buscarPersonaje(friend))

    optimizar = teamOptimizer(personajes)
    equipo = optimizar.optimizador()

    return templates.TemplateResponse("team.html", {"request": request, "id1": personajes[0][0], "id2": personajes[1][0], "id3": personajes[2][0], "id4": personajes[3][0], "id5": personajes[4][0], "id6": personajes[5][0], "id7": personajes[6][0],"id8": equipo[0][0], "id9": equipo[1][0], "id10": equipo[2][0], "id11": equipo[3][0], "id12": equipo[4][0], "id13": equipo[5][0], "id14": equipo[6][0]})

@app.get("/character/{id}")
async def team(id : int):
    return {"lider" : id}

handler = Mangum(app)
