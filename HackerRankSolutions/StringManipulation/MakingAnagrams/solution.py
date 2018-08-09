def makeAnagram(a,b):
    list_a = list(a)
    list_b = list(b)
    list_a.sort()
    list_b.sort()
    a_len = len(list_a)
    b_len = len(list_b)
    a_ind = 0
    b_ind = 0
    delete = 0

    while True:
        if a_ind == a_len:
            return delete + b_len - b_ind
        if b_ind == b_len:
            return delete + a_len - a_ind

        cur_a = list_a[a_ind]
        cur_b = list_b[b_ind]

        if cur_a == cur_b:
            a_ind += 1
            b_ind += 1
        elif cur_a < cur_b:
            delete += 1
            a_ind += 1
        elif cur_b < cur_a:
            delete += 1
            b_ind += 1