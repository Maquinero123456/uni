#David Navarro Jimena 77958167N 3ºB Ingenieria Informatica

import pt
import pb
import pa
import threading
def main():
    trente = threading.Thread(target=pt.ttp)
    alicia = threading.Thread(target=pa.alice)
    bobardo = threading.Thread(target=pb.bob)
    trente.start()
    alicia.start()
    bobardo.start()
    alicia.join()
    
if __name__ == "__main__":
    main()