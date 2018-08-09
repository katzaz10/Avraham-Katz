from Queue import Queue

def listtoqueue(arr):
    '''Converts an list into a queue'''
    que = Queue()
    for i in arr:
        que.put(i)
    return que


def makeAnagram(a, b):
    lista = list(a)
    listb = list(b)
    lista.sort()
    listb.sort()
    aVals = listtoqueue(lista)
    bVals = listtoqueue(listb)
    delete = 0
    cur_a = aVals.get()
    cur_b = bVals.get()

    while True:
        if aVals.empty():
            while not bVals.empty():
                bVals.get()
                delete += 1
            return delete
        if bVals.empty():
            while not aVals.empty():
                aVals.get()
                delete += 1
            return delete
        if cur_a == cur_b:
            if not aVals.empty():
                cur_a = aVals.get()
            if not bVals.empty():
                cur_b = bVals.get()
        elif cur_a < cur_b:
            while not aVals.empty() and cur_a < cur_b:
                delete += 1
                cur_a = aVals.get()
        elif cur_b < cur_a:
            while not bVals.empty() and cur_b < cur_a:
                delete += 1
                cur_b = bVals.get()