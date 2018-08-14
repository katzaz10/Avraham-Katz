def insertNodeAtPosition(head, data, position):
    ins_node = SinglyLinkedListNode(data)
    if head is None and position == 0:
        return ins_node
    cur_node = head
    for i in range(position - 1):
        cur_node = cur_node.next
    ins_node.next = cur_node.next
    cur_node.next = ins_node
    return head