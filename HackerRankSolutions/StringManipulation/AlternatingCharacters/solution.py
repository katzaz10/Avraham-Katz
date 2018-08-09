def alternatingCharacters(s):
    delete = 0
    cur = s[0]

    for i in range(1, len(s)):
        prev = cur
        cur = s[i]
        if prev == cur:
            delete += 1
    return delete