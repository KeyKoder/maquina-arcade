# Hanoi


def hanoi(disco, origen, dest, aux):
    if disco==1:
        print(f"mover {disco} de {origen} a {dest}")
    else:
        hanoi(disco-1, origen, aux, dest)
        print(f"mover {disco} de {origen} a {dest}")
        hanoi(disco-1, aux, dest, origen)


hanoi(5, 1, 3, 2)
