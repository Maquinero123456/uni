import React from 'react'

export default async function handler(req, res){
    let valor = req.body;
    valor = valor.replace(" ", "%20");
    const resultado = await fetch("https://musicbrainz.org/ws/2/artist/?query=artist:"+valor, {headers:{Accept:"application/json"}})
    const data = await resultado.json()

    res.status(200).json(data.artists)
}