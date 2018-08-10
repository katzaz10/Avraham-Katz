def rightmoves(grid, start_x, start_y, hit):
    '''finds all possible moves to the right'''
    moves = []
    row = grid[start_x]
    for y in range(start_y + 1, len(row)):
        if not hit.get((start_x, y), False):
            if row[y] == 'X':
                break
            else:
                moves.append((start_x, y))
                hit[(start_x, y)] = True
    return moves


def leftmoves(grid, start_x, start_y, hit):
    '''finds all possible moves to the left'''
    moves = []
    row = grid[start_x]
    for y in range(start_y - 1, -1, -1):
        if not hit.get((start_x, y), False):
            if row[y] == 'X':
                break
            else:
                moves.append((start_x, y))
                hit[(start_x, y)] = True
    return moves


def upmoves(grid, start_x, start_y, hit):
    '''finds all possible moves upwards'''
    moves = []
    for x in range(start_x - 1, -1, -1):
        if not hit.get((x, start_y), False):
            if grid[x][start_y] == 'X':
                break
            else:
                moves.append((x, start_y))
                hit[(x, start_y)] = True
    return moves


def downmoves(grid, start_x, start_y, hit):
    '''finds all possible moves downwards'''
    moves = []
    for x in range(start_x + 1, len(grid)):
        if not hit.get((x, start_y), False):
            if grid[x][start_y] == 'X':
                break
            else:
                moves.append((x, start_y))
                hit[(x, start_y)] = True
    return moves


def minimumMoves(grid, startX, startY, goalX, goalY):
    goal = (goalX, goalY)
    moves = 0
    hit = {}
    level = [(startX, startY)]
    while True:
        next_level = []
        for point in level:
            if point[0] == goalX and point[1] == goalY:
                return moves
            next_level = next_level + \
                         leftmoves(grid, point[0], point[1], hit) + \
                         rightmoves(grid, point[0], point[1], hit) + \
                         upmoves(grid, point[0], point[1], hit) + \
                         downmoves(grid, point[0], point[1], hit)
            level = next_level
        moves += 1