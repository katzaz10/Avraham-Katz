def paths(root):
    #maps all paths in a tree to lead nodes
    map = {}
    map_paths(root.left, '0', map)
    map_paths(root.right, '1', map)
    return map


def map_paths(node, curpath, map):
    #If node is leaf, maps the node's value. If node is internal node, maps right and left path of children
    if node is not None:
        if node.left is None and node.right is None:
            map[curpath] = node.data
        else:
            map_paths(node.left, curpath + '0', map)
            map_paths(node.right, curpath + '1', map)


def decipher(str, map):
    #deciphers the given string using the paths:char in map
    deciphered = ''
    index = 0
    map_keys = map.keys()
    while index < len(str):
        sub_str = str[index]
        index += 1
        while sub_str not in map_keys:
            sub_str += str[index]
            index += 1
        deciphered += map[sub_str]
    return deciphered


def decodeHuff(root, s):
    map = paths(root)
    print decipher(s, map)