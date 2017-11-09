package com.henu.feifei;
/**
	*@ClassName:VendingMachine
	*@Description:TODO
	*@author:feifei
	*@date :2017年11月9日-下午7:42:27
	*@version:1.0
	*/

import static com.henu.feifei.Input.*;
import java.util.EnumMap;
import java.util.*;
import com.henu.feifei.utils.Print;

enum Category{
	MONEY(NICKEL,DIME,QUARTER,DOLLER),
	ITEM_SELECTION(TOOTHPASTE,CHIPS,SODA,SOAP),
	QUIT_TRANSACTION(ABORT_TRANSACTION),
	SHUT_DOWN(STOP);
	private Input[] values;
	Category(Input...types) {
		values=types;
		// TODO Auto-generated constructor stub
	}
	private static EnumMap<Input, Category> categories=new EnumMap<>(Input.class);
	static {
		for(Category c:Category.class.getEnumConstants()) {
			for(Input type:c.values) {
				categories.put(type,c);
			}
		}
	}
	public static Category categorize(Input input) {
		return categories.get(input);
	}
}
public class VendingMachine {
	private static State state=State.RESTING;
	private static int amount=0;
	private static Input selection=null;
	enum StateDuration{
		TRANSIENT
	}
	enum State{
		RESTING{
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount+=input.amount();
					break;
				case SHUT_DOWN:
					state=TERMINAL;
					break;
				default:
					break;
				}
			}
		},
		ADDING_MONEY{
			void next(Input input) {
				switch (Category.categorize(input)) {
				case MONEY:
					amount+=input.amount();
					break;
				case ITEM_SELECTION:
					selection=input;
					if(amount<selection.amount()) {
						Print.print("insufficient money for "+selection);
					}
					else {
						state=DISPENSING;
					}
					break;
				case QUIT_TRANSACTION:
					state=DISPENSING;
					break;
				case SHUT_DOWN:
					state=TERMINAL;
					break;
				default:
					break;
				}
			}
		},
		DISPENSING(StateDuration.TRANSIENT){
			void next() {
				Print.print("here is your "+selection);
				amount-=selection.amount();
				state=DIVING_CHANGE;
			}
		},
		DIVING_CHANGE(StateDuration.TRANSIENT){
			void next() {
				if (amount>0) {
					Print.print("your change :"+amount);
					amount=0;
				}
				state=State.RESTING;
			}
		},
		TERMINAL{
			void output() {
				Print.print("Halted");
			}
		};
		private boolean isTransient=false;
		private State() {
			// TODO Auto-generated constructor stub
		}
		State(StateDuration trans){
			isTransient=true;
		}
		void next(Input input) {
			throw new RuntimeException("only call next(Input input) for non-transient=states");
		}
		void next() {
			throw new RuntimeException("Only call next() for Stateduration.Transing states");
		}
		void output() {
			Print.print(amount);
		}
	}
	static void run(Generator<Input> gen) {
		while(state!=State.TERMINAL) {
			state.next(gen.next());
			while(state.isTransient) {
				state.next();
			}
			state.output();
		}
	}
	public static void main(String[] args) {
		Generator<Input> gen=new RandomInputGenerator();
		if (args.length==1) {
			state.next();
		}
		run(gen);
				
	}
}


class RandomInputGenerator implements Generator<Input>{
	public Input next() {
		return Input.randomSelection();
	}
}

class FileInputGenerator implements Generator<Input>{
	private Iterator<String> input;
	public FileInputGenerator(String filename) {
		input=new TextFile(filename,";").iterator();
	}
	public Input next() {
		if(!input.hasNext()) {
			return null;
		}
		return Enum.valueOf(Input.class, input.next().trim());
	}
}