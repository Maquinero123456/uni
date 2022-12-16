import {getAccessToken} from '../../lib/spotify';
import {getSession} from 'next-auth/react';

const handler = async (req, res) => {
  return await getAccessToken;
};

export default handler;