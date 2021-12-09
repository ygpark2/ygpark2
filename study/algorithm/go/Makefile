NAME	=		algorithm

BIN		=		go

RM		=		rm -rf

TREE_SRC		=		cmd/tree/main.go \
						cmd/tree/bst.go \
						cmd/tree/delete.go \
						cmd/tree/exists.go \
						cmd/tree/insert.go \
						cmd/tree/show.go

TREE_NAME	=		tree

all:
		$(BIN) build -o $(NAME) $(SRC)

tree:
		$(BIN) build -o $(TREE_NAME) $(TREE_SRC)

clean:		
		$(RM) $(NAME) *~ *#

re:		
		make clean
		make all