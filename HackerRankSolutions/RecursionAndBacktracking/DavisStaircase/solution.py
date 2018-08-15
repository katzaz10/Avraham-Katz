def step_up(cur_step, n, ways_from_step):
    '''Recursively finds how many steps from the current step to the top step. If already knows number of ways
    from current step to top step, as stored in ways_from_step, returns that value'''
    if cur_step == n:
        return 1
    if cur_step > n:
        return 0
    if ways_from_step.get(cur_step, None) is not None:
        return ways_from_step[cur_step]
    ways_from_step[cur_step] = step_up(cur_step + 1, n, ways_from_step) + \
                               step_up(cur_step + 2, n, ways_from_step) + \
                               step_up(cur_step + 3, n, ways_from_step)
    return ways_from_step[cur_step]


def stepPerms(n):
    dict = {}
    return step_up(0, n, dict)