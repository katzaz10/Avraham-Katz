from collections import Counter

def freqQuery(queries):
    freq = Counter()
    freq_count = Counter()
    return_list = []

    for query in queries:
        if query[0] == 1:
            freq_count[freq[query[1]]] -= 1
            freq[query[1]] += 1
            freq_count[freq[query[1]]] += 1
        elif query[0] == 2:
            if freq[query[1]] > 0:
                freq_count[freq[query[1]]] -= 1
                freq[query[1]] -= 1
                freq_count[freq[query[1]]] += 1
        else:
            if freq_count[query[1]] > 0:
                return_list.append(1)
            else:
                return_list.append(0)
    return return_list