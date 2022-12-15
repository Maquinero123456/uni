import {useSession, signIn, signOut, getSession} from 'next-auth/react';

export default function Home({user}) {
  if (user) {
    return (
      <>
        
        Signed in as {user.name} <br />
        
        <button onClick={() => signOut()}>Sign out</button>
      </>
    );
  }
  return (
    <>
      Not signed in <br />
      <button onClick={() => signIn()}>Sign in</button>
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