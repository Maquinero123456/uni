import {getAccessToken} from '../../lib/spotify';
import {getSession} from 'next-auth/react';

const handler = async (req, res) => {
    console.log(getAccessToken);
  return await getAccessToken;
};

export default handler;