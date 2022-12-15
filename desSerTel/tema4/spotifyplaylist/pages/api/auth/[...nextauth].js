import SpotifyProviders from 'next-auth/providers/spotify'

const options={
  providers : [
    Spotify({
      clientId: process.env.CLIENT_ID,
      clientSecret: process.env.CLIENT_SECRET,
      profile(profile){
        return {
          if: profile.id,
          name: profile.display_name,
          email: profile.email,
          image: profile.images?.[0]?.url
        }
      }
    })
  ]
}