import { useState } from "react";
import Link from "next/link";
export default function HomePage(){
    const [result, setResult]=useState(null);
    const [loading, setLoading]=useState(null);
    const [error, setError]=useState(null);
    const [formData, setFormData]=useState({nombre:""});

    const handleSubmit = async (event) => {
        event.preventDefault();
        setFormData(event.target[0].value);
        setLoading(true);
        setError(null);

        try {
            console.log("Llega hasta aqui")
            const res = await fetch('/api/search', {
                method:'POST',
                body: JSON.stringify(event.target[0].value),
                headers: {
                    'Content-Type':'application/json',
                },
            });
            const data = await res.json();
            console.log(data);
            setResult(data);
        }catch(error){
            setError(error);
        }
        setLoading(false);
    };



    return (
        <form onSubmit={handleSubmit}>
            <input type="text" id="nombre" name="nombre" />
            <button type="submit">Buscar</button>
            {loading && (<p>Cargando</p>)}
            {!loading && result && 
                <div>
                    <table>
                        <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Pais</th>
                            <th>Descripcion</th>
                        </tr>
                        </thead>
                        <tbody>
                        {result.map((item)=>{
                        return(<tr>
                            <td><Link href={{pathname:'/canciones', query:{id:item.id, nombre:item.name}}}>{item.name}</Link></td>
                            <td>{item.country}</td>
                            <td>{item.disambiguation}</td>
                        </tr>)
                        })}
                        </tbody>
                    </table>
                </div>}
        </form>
    )
}