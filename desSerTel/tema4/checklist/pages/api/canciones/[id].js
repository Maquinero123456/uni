export default async function handler(req, res){
    let url = "https://musicbrainz.org/ws/2/work?artist="+req.query.id+"&fmt=json&limit=100"
    const resultado = await fetch(url, {headers:{Accept:"application/json"}})
    const data = await resultado.json()
    var canciones = data.works
    const count = data["work-count"]
    var offset = 100
    while(offset<count){
        url = "https://musicbrainz.org/ws/2/work?artist="+req.query.id+"&fmt=json&limit=100&offset="+offset
        let resultado2 = await fetch(url, {headers:{Accept:"application/json"}})
        let data2 = await resultado2.json()
        canciones = canciones.concat(data2.works)
        offset+=100
    }
    res.status(200).json(canciones)
}