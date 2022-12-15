import SpotifyWebApi from "spotify-web-api-node";

const spotifyWebApi = new SpotifyWebApi({
    clientId: process.env.CLIENT_ID,
    clientSecret: process.env.CLIENT_SECRET,
})

export default spotifyWebApi;