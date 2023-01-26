import requests
import json

class characterSearch:
    def __init__(self):
        self.url = "https://dokkan.fyi/characters/"
        self.awakening = ["Super", "Extreme"]
        self.type = ["AGL", "TEQ", "INT", "STR", "PHY"]

    #Search desired character
    def buscarPersonaje(self, id : int) -> list:
        #Create url
        try:
            url = self.url+str(id)
        except TypeError:
            print("Id debe ser tipo int")

        querystring = {"":""}

        headers = {
            "x-inertia": "true",
            "x-inertia-version": "aa6e2214730f2161a1f77c9fa9074052"
        }

        response = requests.request("GET", url, headers=headers, params=querystring)

        #If character doesnt exist throw error
        if(response.status_code!=200):
            raise TypeError("Character doesnt exist or wrong code")

        #Add character name to list
        nombre = response.json()["props"]["card"]["name"]
        subnombre = response.json()["props"]["card"]["leader_skill_set"]["name"]
        tipo = response.json()["props"]["card"]["element"]
        awakeningType = response.json()["props"]["card"]["awakening_element_type"]
        nombre=nombre.replace('\n', '')
        dict = [subnombre+" "+nombre+" "+str(self.awakening[int(awakeningType)-1])+" "+str(self.type[int(tipo)])+" ("+str(id)+")"]
        dict.append(response.json()["props"]["card"]["link_skill_ids"])
        #Return list [Name, [Links skills]]
        return dict