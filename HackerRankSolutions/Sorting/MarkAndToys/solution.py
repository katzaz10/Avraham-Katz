def maximumToys(prices, k):
    prices.sort()
    max_toys = 0
    cur_spent = 0
    for i in range(len(prices)):
        cur_spent += prices[i]
        if cur_spent < k:
            max_toys += 1
        else:
            return max_toys