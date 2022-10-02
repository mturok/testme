import numpy as np

def cube(rug, dim:int, row:int, col:int): 
  for r in range(dim):
    for c in range(dim):
      rug[r+row][c+col] = 1
  return row + dim - 1


def line(rug, row:int):
  (height, width)= rug.shape
  for c in range(width):
     rug[row][c] = 1
  return row


def triangle(rug, dim:int, row:int, col:int, invert:bool=False):
  row_list = list(range(dim))
  for r in row_list:
    # compute the number of columns based on our row
    span = r if not invert else dim - r - 1
    col_list = list(range(col-span, col+span+1))
    for c in col_list:
      rug[row+r][c] = 1
  return row + dim - 1


def diamond(rug, dim:int, row:int, col:int):
  row = triangle(rug, dim, row, col)
  row = triangle(rug, dim, row, col, invert=True)
  return row


def diamond_row(rug, dim:int, row:int):
  (height, width)= rug.shape
  for c in range(dim, width - dim, 2 * dim-1):
    last_row = diamond( rug, dim, row, c)
  return last_row


def triangle_row(rug, dim:int, row:int, invert:bool=False):
  (height, width)= rug.shape
  for c in range(dim, width - dim, 2 * dim-1): # start/stop/step
    last_row = triangle(rug, dim, row, c, invert=invert)
    invert = not invert
  return last_row


def cube_row(rug, dim:int, row:int, col_start=0):
  # compute number of cubes
  (height, width)= rug.shape
  for c in range(col_start, width, dim*2): # start/stop/step
    last_row = cube(rug, dim, row, c)
  return last_row


def printme(rug):
  (height, width) = rug.shape
  for r in range(height):
    for c in range(width):
      print('%' if rug[r][c] else ' ', end="")
    print()


HEIGHT = 30
WIDTH = 30


rug = np.zeros((HEIGHT, WIDTH))
row = line(         rug,    0)
row = line(         rug,    row+1)
row = triangle_row( rug, 3, row+2, False)
row = line(         rug,    row+2)
row = cube_row(     rug, 2, row+1)
row = diamond_row(  rug, 4, row+1)
row = cube_row(     rug, 2, row+1)
row = line(         rug,    row+1)
row = triangle_row( rug, 3, row+2, True)
row = line(         rug,    row+2)
row = line(         rug,    row+1)


printme(rug)

