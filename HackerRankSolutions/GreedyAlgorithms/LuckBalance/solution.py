def luckBalance(k, contests):
    luck = 0
    important = []
    for contest in contests:
        luck += contest[0]
        if contest[1] == 1:
            important.append(contest[0])
    important.sort()
    for i in range(0, len(important) - k):
        luck -= important[i] * 2
    return luck