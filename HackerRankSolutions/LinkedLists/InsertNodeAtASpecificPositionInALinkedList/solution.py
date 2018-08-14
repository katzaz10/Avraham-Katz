def insertNodeAtPosition(head, data, position):
    insert = SinglyLinkedListNode(data)
    if head is None and position == 0:
        return insert
    cur_node = head
    for i in range(position - 1):
        cur_node = cur_node.next
    insert.next = cur_node.next
    cur_node.next = insert
    return head