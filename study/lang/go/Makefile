NAME	=		algorithm

BIN		=		go

RM		=		rm -rf

TREE_SRC	=	cmd/main.go \
				internal/tree/bst.go \
				internal/tree/delete.go \
				internal/tree/exists.go \
				internal/tree/insert.go \
				internal/tree/show.go

TREE_NAME	=	tree

all:
		$(BIN) build -o $(NAME) $(SRC)

tree:
		$(BIN) build -o $(TREE_NAME) $(TREE_SRC)

clean:		
		$(RM) $(NAME) *~ *#

re:		
		make clean
		make all