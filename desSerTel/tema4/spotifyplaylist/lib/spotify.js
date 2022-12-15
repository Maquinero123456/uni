const client_id = process.env.CLIENT_ID;
const client_secret = process.env.CLIENT_SECRET;
const basic = Buffer.from('${client_id}:${client_secret}').toString('base64');
const TOKEN_ENDPOINT = 'https://accounts.spotify.com/api/token';
const PLAYLISTS_ENDPOINT = 'https://api.spotify.com/v1/me/playlists';

const getAccessToken = async (refresh_token) => {
    const response = await fetch(TOKEN_ENDPOINT, {
        method: 'POST',
        headers: {
        'Authorization': 'Basic ' +{basic},
        'Content-Type': 'application/x-www-form-urlencoded',
        },
        form: {
            grant_type: 'authorization_code',
        },
    });
    console.log(response.body);
    return response.json();
};

export const getUsersPlaylists = async (refresh_token) => {
    const {access_token} = await getAccessToken(refresh_token);
    console.log({access_token});
    return fetch(PLAYLISTS_ENDPOINT, {
        method: 'GET',
        headers: {
        Authorization: 'Bearer '+{access_token},
        Accept: 'application/json',
        'Content-Type': 'application/json',
        },
    });
};