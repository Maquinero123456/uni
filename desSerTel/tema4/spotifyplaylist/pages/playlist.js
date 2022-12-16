import {useSession, signIn, signOut, getSession} from 'next-auth/react';

async function getData(token) {
    const res = await fetch('http://localhost:3000/api/playlist?token='+token);
    // The return value is *not* serialized
    // You can return Date, Map, Set, etc.
  
    return res;
  }

export default async function Playlist({user}) {
    const res = await fetch('https://api.spotify.com/v1/me/playlists', {
        headers: {
          Authorization: `Bearer ${user.accessToken}`,
        },
      }).then((res) => res.json());
    const data = res;
    return (
        <>
          
          <p>{JSON.stringify(await data.items[0].name)}</p>
        </>
      );
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