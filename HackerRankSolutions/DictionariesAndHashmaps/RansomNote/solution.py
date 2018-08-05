def checkMagazine(magazine, note):
    mag_words = {}
    for word in magazine:
        mag_words[word] = mag_words.setdefault(word, 0) + 1

    for word in note:
        if mag_words.setdefault(word, 0) == 0:
            print 'No'
            return
        else:
            mag_words[word] = mag_words.get(word) - 1

    print 'Yes'