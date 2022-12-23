import {useSession, signIn, signOut, getSession} from 'next-auth/react';
import {useState, useEffect} from 'react';
import Script from 'next/script'

export default function Playlist({user}) {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
  
    useEffect(() => {
      fetch('https://api.spotify.com/v1/me/playlists', {
          method: "GET",
          headers: {
            Authorization: 'Bearer '+user.accessToken,
            Accept: 'application/json', 
            'Content-Type': 'application/json'
          },
        })
        .then((response) => response.json())
        .then((dog) => {
          setData(dog.items);
          setIsLoading(false);
        });
    }, []);
    if (isLoading) { 
      return (
        <div className="App">
          <h1>Cargando...</h1>
        </div>
      );
    }
    return (
      <main>
      <div id="playlist">
        <Script
          onLoad={()=> {
            const table = document.createElement('table');
            table.createTHead("Nombre");
            table.createTHead("Descripcion");
            table.createTHead("Id");
            for(let i = 0; i<data.length; i++){
              console.log(data[i].name);
              let row= table.insertRow();
              row.insertCell(0).innerHTML = data[i].name;
              row.insertCell(1).innerHTML = data[i].description;
              row.insertCell(2).innerHTML = data[i].id;
            }
            document.append(table);
          }}>
          
        </Script>
      </div>
      </main>
    )
    
    
}

export async function getServerSideProps(ctx) {
  const session = await getSession(ctx)
  if (!session) {
    return {
      props: {}
    }
  }
  const { user } = session;
  return {
    props: { user },
  }
}