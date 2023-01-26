import Head from 'next/head'
import Image from 'next/image'
import { Inter } from '@next/font/google'
import styles from '../styles/Home.module.css'
import useSWR from 'swr'
import {useRouter} from 'next/router'
import { useState } from 'react'
import { useEffect } from 'react'

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
    const router = useRouter();
    const id = router.query.id;
    const [names, setNames] = useState([]);
    const [struckOutNames, setStruckOutName] = useState(new Set());

    useEffect(() => {
      async function fetchNames(){
        try{
            const res = await fetch(`/api/canciones/${id}`, {headers:{Accept:"application/json"}})
            const data = await res.json();
            const titulos = [];
            data.map((item)=>{
                titulos.push(item.title)
            })
            setNames(titulos)
        }catch(error){}
      }
      fetchNames()
    }, [`/api/canciones/${id}`])

    return(
        <div>
            <h1>{router.query.nombre}</h1>
            <table>
                <tbody>
                    {names.map((name)=>(
                        <tr key={name} style={struckOutNames.has(name) ? {textDecoration:'line-through'}:{}}>
                            <td>
                                <button
                                onClick={()=>{
                                    setStruckOutName((prev)=>{
                                        const newSet= new Set(prev);
                                        newSet.add(name);
                                        return newSet;
                                    });
                                }}>
                                    Tachar
                                </button>
                            </td>
                            <td>{name}</td>
                            
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
    
}
