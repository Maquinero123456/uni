import spotifyWebApi, {getUsersPlaylists} from '../../lib/spotify';
import {getSession} from 'next-auth/react';

const handler = async (req, res) => {
  const {
    user: {accessToken},
  } = await getSession({req});
  spotifyWebApi.setAccessToken(accessToken);
  spotifyWebApi.getMe().then(
    function(data) {
      console.log(data.body);
      return <p>{data.body}</p>;
    },
    function(err) {
      return(err);
    }
  );
};

export default handler;