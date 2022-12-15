import NextAuth from "next-auth";
import SpotifyProvider from "next-auth/providers/spotify";
import spotifyWebApi from "../../../lib/spotify";

const scope =
  "user-read-recently-played user-read-playback-state user-top-read user-modify-playback-state user-read-currently-playing user-follow-read playlist-read-private user-read-email user-read-private user-library-read playlist-read-collaborative";

export default NextAuth({
  providers: [
    SpotifyProvider({
      clientId: process.env.CLIENT_ID,
      clientSecret: process.env.CLIENT_SECRET,
      authorization: {
        params: { scope },
      },
    }),
  ],
  secret: process.env.NEXTAUTH_SECRET,
  callbacks: {
    async jwt({ token, account, user }) {
      if (account) {
        token.expires_at = account.expires_at;
        token.accessToken = account.access_token;
      }
      spotifyWebApi.setAccessToken(token.accessToken);
      return token;
    },
    async session({ session, token }) {
      session.user = token;
      return session;
    },
  },
});