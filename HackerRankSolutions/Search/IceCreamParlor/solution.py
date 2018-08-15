def whatFlavors(cost, money):
    cost_to_index = {}
    for i in range(len(cost)):
        cost_to_index[cost[i]] = cost_to_index.get(cost[i], []) + [i + 1]
    costs = cost_to_index.keys()
    for c in costs:
        dif = money - c
        if dif == c and len(cost_to_index[c]) > 1:
            i1 = cost_to_index[c][0]
            i2 = cost_to_index[c][-1]
            if i1 > i2:
                print str(i2) + ' ' + str(i1)
                break
            print str(i1) + ' ' + str(i2)
            break
        if cost_to_index.get(dif, []):
            i1 = cost_to_index[c][0]
            i2 = cost_to_index[dif][0]
            if i1 > i2:
                print str(i2) + ' ' + str(i1)
                break
            print str(i1) + ' ' + str(i2)
            break