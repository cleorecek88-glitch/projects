def parse_fen(fen): # Implement no.1
    fen_pieces, to_move, castling_rights, ep, hm, fm = fen.split(" ")
    board = []

    for row in fen_pieces.split("/"):
        board_row = []
        for char in row:
            if char.isdigit():
                board_row.extend(["_"] * int(char))
            else:
                board_row.append(char)
        board.append(board_row)

    return {
        "board": board, #rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
        "to_move": to_move, # W or b
        "castling_rights": castling_rights, # KQkq
        "en_passant": ep, # -
        "halfmove_clock": hm, # 0
        "fullmove_number": fm, # 1
    }

fen_itemsW = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
piece_position = parse_fen(fen_itemsW)
print(piece_position)

fen_itemsB = "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1"
piece_positionB = parse_fen(fen_itemsB)
print(piece_positionB)


# def generate_moves(board):
#     #raise NotImplementedError("This function is not implemented yet.")



# def apply_move(board, move):
#     raise NotImplementedError("This function is not implemented yet.")
# 