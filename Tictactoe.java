package firstPackage;

import java.util.Scanner;

class tictactoe_board {	
	char[] board;
	
	void initialize_board(int board_len) {
		int board_size = board_len * board_len;
		this.board = new char[board_size];
		
		for(int i=0; i<board_size; i++) {
			this.board[i] = (char)('0' + i);
		}
	}
	
	void print_board(int board_len) {
		for(int row=0; row<board_len; row++) {
			for(int column=0; column<board_len; column++) {
				System.out.print(this.board[row * board_len + column]);
				if(column < board_len - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if(row < board_len - 1) {
				System.out.println("-----");;
			}
		}
		
	}
	
	boolean place_piece(int place, char piece) {
		boolean return_value = true;
		
		if(place <= this.board.length - 1 && this.board[place] != 'X' && this.board[place] != 'O') {
			this.board[place] = piece;
		}
		else {
			return_value = false;
		}
		
		return return_value;
	}

	boolean check_if_won(char piece) {
		boolean won = false;
		char spots_taken = 0;
		char winning_spots[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
		
		for(int j=0; j<winning_spots.length; j++) {
			for(int i=0; i<winning_spots[j].length; i++) {
				if(this.board[winning_spots[j][i]] == piece) {
					spots_taken++;
				}
			}
			if(winning_spots[j].length == spots_taken) {
				won = true;
				break;
			}
			spots_taken = 0;
		}
		
		return won;
	}
	
	int place_computer_piece() {
		char spots_taken = 0;
		int computer_spot = -1;
		char winning_spots[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
		
		for(int j=0; j<winning_spots.length; j++) {
			for(int i=0; i<winning_spots[j].length; i++) {
				if(this.board[winning_spots[j][i]] == 'O') {
					spots_taken++;
				}
				else if(this.board[winning_spots[j][i]] != 'X'){
					computer_spot = winning_spots[j][i];
				}
			}
			if(winning_spots[j].length - 1 == spots_taken && computer_spot != -1) {
				break;
			}
			computer_spot = -1;
			spots_taken = 0;
		}
		
		if(-1 == computer_spot) {
			for(int j=0; j<winning_spots.length; j++) {
				for(int i=0; i<winning_spots[j].length; i++) {
					if(this.board[winning_spots[j][i]] == 'X') {
						spots_taken++;
					}
					else if(this.board[winning_spots[j][i]] != 'O'){
						computer_spot = winning_spots[j][i];
					}
				}
				if(winning_spots[j].length - 1 == spots_taken && computer_spot != -1) {
					break;
				}
				computer_spot = -1;
				spots_taken = 0;
			}
		}
		if(-1 == computer_spot) {
			for(int j=0; j<winning_spots.length; j++) {
				for(int i=0; i<winning_spots[j].length; i++) {
					if(this.board[winning_spots[j][i]] == 'O') {
						spots_taken++;
					}
					else if(this.board[winning_spots[j][i]] != 'X'){
						computer_spot = winning_spots[j][i];
					}
				}
				if(winning_spots[j].length - 2 == spots_taken && computer_spot != -1) {
					break;
				}
				computer_spot = -1;
				spots_taken = 0;
			}
		}
		if(-1 == computer_spot) {
			for(int i=0; i<9; i++) {
				if(this.board[i] != 'X' && this.board[i] != 'O') {
					computer_spot = i;
					break;
				}
			}
		}
		
		return computer_spot;
		
	}
}

public class Tictactoe {
	static int get_integer_input(String prompt) {
		Scanner scanner_obj = new Scanner(System.in);
		int input = 0;
		
		System.out.println(prompt);
		input = scanner_obj.nextInt();
		
		//scanner_obj.close();
		
		return input;
	}
	
	public static void main(String[] argv) {
		tictactoe_board board_obj = new tictactoe_board();
	
		char piece = 0;
		int piece_position = 0;
		int board_len = 3;
		boolean legal_placement = false;
		boolean game_won = false;
		String prompt;
		
		board_obj.initialize_board(board_len);

		for(int i=0; i<board_len * board_len && !game_won; i++) {
			board_obj.print_board(board_len);
			
			if(i % 2 == 0) {
				piece = 'X';
			}
			else {
				piece = 'O';
			}
			
			while(true) {
				if('X' == piece) {
					prompt = String.format("%c: Where would you like to go?", piece);
					piece_position = get_integer_input(prompt);
				}
				else {
					piece_position = board_obj.place_computer_piece();
				}
				legal_placement = board_obj.place_piece(piece_position, piece);
				System.out.println();
				if(legal_placement) {
					break;
				}
				else {
					System.out.print("That is not a valid place. ");
				}
			}
			
			game_won = board_obj.check_if_won(piece);
		}

		if(game_won) {
			prompt = String.format("%c Wins!", piece);
			System.out.println(prompt);
		}
		else {
			System.out.println("It's a tie...");
		}
	}
}
