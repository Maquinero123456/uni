import itertools

#TODO Media

class teamOptimizer:
    #Create class with starting team
    def __init__(self, team):
        self.team = team
        self.links = self.checkLinks(team)
        self.linksMedia = self.checkLinksMedia(team)

    #Checks best team between all posible permutations
    def optimizador(self):
        teamList = list(self.team)
        leader = teamList[0]
        friend = teamList[6]
        slice = teamList[1:6]
        #Create all possible permutations
        permutaciones = list(itertools.permutations(slice))
        #Check all permutations
        for i in range(len(permutaciones)):
            rotacion = []
            rotacion+= [leader]
            rotacion+= permutaciones[i]
            rotacion+= [friend]
            links = self.checkLinks(rotacion)
            #A = new team, b = old team. Checks all links and sees who have better
            a, b = 0,0
            for i in range(len(links)):
                if(links[i]>self.links[i]):
                    a+=1
                elif(self.links[i]>links[i]):
                    b+=1
            #If new team is better exchange them with base team
            if a > b :
                self.team = rotacion
                self.links = links

        return self.team

    #Optimizador Media
    def optimizadorMedia(self):
        teamList = list(self.team)
        leader = teamList[0]
        friend = teamList[6]
        slice = teamList[1:6]
        #Create all possible permutations
        permutaciones = list(itertools.permutations(slice))
        #Check all permutations
        for i in range(len(permutaciones)):
            rotacion = []
            rotacion+= [leader]
            rotacion+= permutaciones[i]
            rotacion+= [friend]
            links = self.checkLinksMedia(rotacion)

            #If new team is better exchange them with base team
            if links > self.linksMedia :
                self.team = rotacion
                self.links = links

        return self.team

    #Check all links of a team in a rotation and return a list with the number of links
    def checkLinks(self, team : list) -> list:
        personajesList = team
        teamLinks = (self.linkComparator(personajesList[0][1], personajesList[1][1], personajesList[4][1]))
        teamLinks+=(self.linkComparator(personajesList[2][1], personajesList[3][1], personajesList[5][1]))
        teamLinks+=(self.linkComparator(personajesList[0][1], personajesList[1][1], personajesList[6][1]))
        teamLinks+=(self.linkComparator(personajesList[2][1], personajesList[3][1], personajesList[4][1]))
        teamLinks+=(self.linkComparator(personajesList[0][1], personajesList[1][1], personajesList[5][1]))
        teamLinks+=(self.linkComparator(personajesList[2][1], personajesList[3][1], personajesList[6][1]))
        return teamLinks

    def checkLinksMedia(self, team : list) -> int:
        personajesList = team
        teamLinks = (self.linkComparator(personajesList[0][1], personajesList[1][1], personajesList[4][1]))
        teamLinks+=(self.linkComparator(personajesList[2][1], personajesList[3][1], personajesList[5][1]))
        teamLinks+=(self.linkComparator(personajesList[0][1], personajesList[1][1], personajesList[6][1]))
        teamLinks+=(self.linkComparator(personajesList[2][1], personajesList[3][1], personajesList[4][1]))
        teamLinks+=(self.linkComparator(personajesList[0][1], personajesList[1][1], personajesList[5][1]))
        teamLinks+=(self.linkComparator(personajesList[2][1], personajesList[3][1], personajesList[6][1]))
        return sum(teamLinks)

    #Check number of links between three characters and return then in a list [character 1 - character 2, character 2 - character 3]
    def linkComparator(self, links1 : list, links2:list, links3:list) -> list:
        links = []
        a = 0
        if len(links1)>len(links2):
            for i in range(len(links1)):
                for j in range(len(links2)):
                    if links1[i] == links2[j]:
                        a+=1
        else:
            for i in range(len(links2)):
                for j in range(len(links1)):
                    if links1[j] == links2[i]:
                        a+=1
        links.append(a)
        a = 0
        if len(links2)>len(links3):
            for i in range(len(links2)):
                for j in range(len(links3)):
                    if links2[i] == links3[j]:
                        a+=1
        else:
            for i in range(len(links3)):
                for j in range(len(links2)):
                    if links2[j] == links3[i]:
                        a+=1
        links.append(a)
        return links