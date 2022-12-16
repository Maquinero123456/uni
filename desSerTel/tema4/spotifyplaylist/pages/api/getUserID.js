import spotifyWebApi, {getUsersPlaylists} from '../../lib/spotify';
import {getSession} from 'next-auth/react';

const handler = async (req, res) => {
  const {
    user: {accessToken},
  } = await getSession({req});
  spotifyWebApi.setAccessToken(accessToken);
  const result = "";
  spotifyWebApi.getMe().then(
    async function(data) {
      return data.body.id;
    },
    function(err) {
      return(err);
    }
  );
 
};

export default handler;