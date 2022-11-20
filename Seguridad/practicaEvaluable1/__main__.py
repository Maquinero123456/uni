import alice
import bob
import trent
import threading
def main():
    trente = threading.Thread(target=trent.trent)
    alicia = threading.Thread(target=alice.alice)
    bobardo = threading.Thread(target=bob.bob)
    trente.start()
    alicia.start()
    bobardo.start()
    alicia.join()
    
if __name__ == "__main__":
    main()