/*DDL for a PostgreSQL database. */



INSERT INTO public.category (id, type) VALUES (2, 'SQL');
INSERT INTO public.category (id, type) VALUES (3, 'Networking');
INSERT INTO public.category (id, type) VALUES (8, 'Programming');
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (9, 'MySQL', 2);
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (10, 'Postgresql', 2);
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (11, 'TCP', 3);
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (12, 'UDP', 3);
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (17, 'Java', 8);
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (18, 'C#', 8);
INSERT INTO public.sub_category (id, type_name, category_id) VALUES (19, 'C++', 8);
INSERT INTO public.topic (id, topic_name, subcategory_id) VALUES (13, 'Java Syntax', 17);
INSERT INTO public.topic (id, topic_name, subcategory_id) VALUES (14, 'Java Core', 17);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (36, 1, 'ADVANCED', 2, '    
What will happen if you add the statement System.out.println(5 / 0); to a working
main() method?', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (37, 1, 'ADVANCED', 2, '    
Which of the following statements are true? (Choose all that apply)', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (38, 3, 'ADVANCED', 6, '    Which of the following exceptions are thrown by the JVM? (Choose all that apply)', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (39, 4, 'ADVANCED', 8, '    Which of the following are true? (Choose all that apply)', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (24, 3, 'BEGINNER', 6, 'Which of the following are valid Java identifiers? (Choose all that apply)', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (25, 1, 'BEGINNER', 2, 'What is the output of the following program?
	1: public class WaterBottle {
	2: private String brand;
	3: private boolean empty;
	4: public static void main(String[] args) {
	5: 	WaterBottle wb = new WaterBottle();
	6: 	System.out.print("Empty = " + wb.empty);
	7: 	System.out.print(", Brand = " + wb.brand);
	8: 	}
	9: }

', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (26, 3, 'BEGINNER', 6, 'Which of the following are true? (Choose all that apply)
	4: short numPets = 5;
	5: int numGrains = 5.6;
	6: String name = "Scruffy";
	7: numPets.length();
	8: numGrains.length();
	9: name.length();
', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (27, 2, 'BEGINNER', 4, 'Given the following class, which of the following is true? (Choose all that apply)
1: public class Snake {
2:
3: public void shed(boolean time) {
4:
5:	if (time) {
6:
7:	}
8:	System.out.println(result);
9:
10: 	}
11: }
', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (28, 2, 'BEGINNER', 4, 'Given the following classes, which of the following can independently replace INSERT IMPORTS HERE to make the code compile? (Choose all that apply)
package aquarium;
public class Tank { }

package aquarium.jellies;
public class Jelly { }

package visitor;
INSERT IMPORTS HERE
public class AquariumVisitor {
	public void admire(Jelly jelly) { } 
}
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (29, 1, 'INTERMEDIATE', 2, 'What is output by the following code? (Choose all that apply)
	1: public class Fish {
	2:	 public static void main(String[] args) {
	3:		int numFish = 4;
	4:		String fishType = "tuna";
	5:		String anotherFish = numFish + 1;
	6:		System.out.println(anotherFish + " " + fishType);
	7:		System.out.println(numFish + " " + 1);
	8: } }
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (30, 3, 'INTERMEDIATE', 6, 'Which are true statements? (Choose all that apply)
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (31, 3, 'INTERMEDIATE', 6, '    Which of the following are output by this code? (Choose all that apply)
	3: String s = "Hello";
	4: String t = new String(s);
	5: if ("Hello".equals(s)) System.out.println("one");
	6: if (t == s) System.out.println("two");
	7: if (t.equals(s)) System.out.println("three");
	8: if ("Hello" == s) System.out.println("four");
	9: if ("Hello" == t) System.out.println("five");
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (34, 1, 'INTERMEDIATE', 2, '    What is the result of the following code?
2: String s1 = "java";
3: StringBuilder s2 = new StringBuilder("java");
4: if (s1 == s2)
5:    System.out.print("1");
6: if (s1.equals(s2))
7:    System.out.print("2");
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (35, 1, 'ADVANCED', 2, '1: public class Dog {
2: public String name;
3: public void parseName() {
4: System.out.print("1");
5: try {
6: System.out.print("2");
7: int x = Integer.parseInt(name);
8: System.out.print("3");
9: } catch (NumberFormatException e) {
10: System.out.print("4");
11: }
12: }
13: public static void main(String[] args) {
14: Dog leroy = new Dog();
15: leroy.name = "Leroy";
16: leroy.parseName();
17: System.out.print("5");
18: } }', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (32, 3, 'BEGINNER', 6, 'Which are true statements? (Choose all that apply)', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (33, 1, 'INTERMEDIATE', 2, '    What is the result of the following code?
7: StringBuilder sb = new StringBuilder();
8: sb.append("aaa").insert(1, "bb").insert(4, "ccc");
9: System.out.println(sb);
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (41, 4, 'ADVANCED', 8, '    Which of the following are true? (Choose all that apply)', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (43, 2, 'ADVANCED', 4, '    Which of the following compile? (Choose all that apply)
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (44, 3, 'ADVANCED', 6, '    Which of the following methods compile? (Choose all that apply)
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (46, 2, 'ADVANCED', 4, '    Given the following method, which of the method calls return 2? (Choose all that apply)
public int howMany(boolean b, boolean... b2) {
	return b2.length;
}
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (47, 1, 'INTERMEDIATE', 2, '    What modifiers are implicitly applied to all interface methods? (Choose all that apply)
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (48, 2, 'INTERMEDIATE', 4, '    Which of the following statements about polymorphism are true? (Choose all that apply)
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (49, 5, 'INTERMEDIATE', 10, '    Which statements are true for both abstract classes and interfaces? (Choose all that apply)', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (50, 1, 'INTERMEDIATE', 2, '    What is the output of the following code?
1: abstract class Reptile {
2: public final void layEggs() { System.out.println("Reptile laying eggs");
 }
3: public static void main(String[] args) {
4: Reptile reptile = new Lizard();
5: reptile.layEggs();
6: }
7: }
8: public class Lizard extends Reptile {
9: public void layEggs() { System.out.println("Lizard laying eggs"); }
10: }
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (51, 1, 'INTERMEDIATE', 2, '    What is the result of the following code?
1: public abstract class Bird {
2: private void fly() { System.out.println("Bird is flying"); }
3: public static void main(String[] args) {
4: Bird bird = new Pelican();
5: bird.fly();
6: }
7: }
8: class Pelican extends Bird {
9: protected void fly() { System.out.println("Pelican is flying"); }
10: }
', 14);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (52, 1, 'BEGINNER', 2, '    What is the output of the following code snippet?

3: int x1 = 50, x2 = 75;
4: boolean b = x1 >= x2;
5: if(b = true) System.out.println("Success");
6: else System.out.println("Failure");
', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (53, 1, 'BEGINNER', 2, '    What is the output of the following code snippet?
3: int x = 4;
4: long y = x * 4 - x++;
5: if(y<10) System.out.println("Too Low");
6: else System.out.println("Just right");
7: else System.out.println("Too High");
', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (54, 3, 'BEGINNER', 6, '    What data type (or types) will allow the following code snippet to compile? (Choose all that
apply)
byte x = 5;
byte y = 10;
z = x + y;
', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (55, 4, 'BEGINNER', 8, '    What change would allow the following code snippet to compile? (Choose all that apply)
3: long x = 10;
4: int y = 2 * x;
', 13);
INSERT INTO public.questions (id, correct_answer_count, level, points, question, topic_entity_id) VALUES (56, 1, 'BEGINNER', 2, '    What is the result of the following code snippet?
3: final char a = ''A'', d = ''D'';
4: char grade = ''B'';
100 Chapter 2 â–  Operators and Statements
c02.indd 1Â½ 5/2014 Page 100
5: switch(grade) {
6: case a:
7: case ''B'': System.out.print("great");
8: case ''C'': System.out.print("good"); break;
9: case d:
10: case ''F'': System.out.print("not good");
11: }
', 13);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (77, 'A$B  ', 'Option A is valid because you can use the dollar sign in identifiers.', true, 24);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (78, '_helloWorld ', '. Option B is
valid because you can use an underscore in identifiers. ', true, 24);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (79, 'true ', 'Option C is not a valid identifier
because true is a Java reserved word', false, 24);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (80, 'java.lang
', 'Option D is not valid because the dot (.) is not
allowed in identifiers. ', false, 24);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (81, 'Public', 'Option E is valid because Java is case sensitive, so Public is not
a reserved word and therefore a valid identifier. ', true, 24);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (82, '1980_s
', '. Option F is not valid because the first
character is not a letter, $, or _.', false, 24);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (83, 'Line 6 generates a compiler error.', '', false, 25);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (84, 'Line 7 generates a compiler error.
', '', false, 25);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (85, 'There is no output.', '', false, 25);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (86, 'Empty = false, Brand = null ', 'Boolean fields initialize to false and references initialize to 		null , so empty is false
	and brand is null . Brand = null is output.
', true, 25);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (87, 'Empty = null, Brand = null', '', false, 25);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (88, 'Empty = false, Brand = copy aneluc chgitem inch exav', '', false, 25);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (96, 'If String result = "done"; is inserted on line 2, the code will compile', 'Adding the variable at line 2 makes result an instance variable. Since instance
variables are in scope for the entire life of the object, option A is correct. ', true, 27);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (97, 'If String result = "done"; is inserted on line 4, the code will compile', '. Option B is
correct because adding the variable at line 4 makes result a local variable with a scope
of the whole method.', true, 27);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (98, 'If String result = "done"; is inserted on line 6, the code will compile.', '. Adding the variable at line 6 makes result a local variable with
a scope of lines 6â€“7. Since it is out of scope on line 8, the println does not compile and
option C is incorrect. ', false, 27);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (99, '. If String result = "done"; is inserted on line 9, the code will compile', 'Adding the variable at line 9 makes result a local variable with
a scope of lines 9 and 10. Since line 8 is before the declaration, it does not compile and
option D is incorrect.', false, 27);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (100, 'None of the above changes will make the code compile.
', '. Finally, option E is incorrect because the code can be made to
compile.
', false, 27);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (101, 'import aquarium.*;', 'Option A is incorrect because it only imports classes in the aquarium packageâ€” Tank
in this caseâ€”and not those in lower-level packages. Option B is incorrect because you
cannot use wildcards anyplace other than the end of an import statement.', false, 28);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (102, 'import aquarium.*.Jelly;', '', false, 28);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (103, 'import aquarium.jellies.Jelly; ', 'Option C is correct because it imports Jelly by classname. ', true, 28);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (104, 'import aquarium.jellies.*;     ', 'Option D is cor-
rect because it imports all the classes in the jellies package, which includes Jelly .
', true, 28);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (105, 'import aquarium.jellies.Jelly.*;', 'Option E is
incorrect because you cannot import parts of a class with a regular import statement.
', false, 28);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (106, 'None of these can make the code compile.', 'Option F is incorrect because options C and D do make the code compile.
', false, 28);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (128, 'An immutable object can be modified.
', '', false, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (129, 'An immutable object cannot be modified.', '', true, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (130, 'An immutable object can be garbage collected', '', true, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (131, 'An immutable object cannot be garbage collected', '', false, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (132, 'String is immutable. ', '', true, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (133, 'StringBuffer is immutable.
', '', false, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (134, 'StringBuilder is immutable', '', false, 30);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (135, '4 1
', '', false, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (136, '41', '', false, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (137, '5', '', false, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (138, '5 tuna
', '', false, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (139, '5tuna', '', false, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (140, '51tuna', '', false, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (141, 'The code does not compile. ', 'Line 5 does not compile. This question is checking to see if you are paying attention
to the types. numFish is an int and 1 is an int . Therefore, we use numeric addition and
get 5. The problem is that we canâ€™t store an int in a String variable. Supposing line 5
said String anotherFish = numFish + 1 + ""; . In that case, the answer would be
options A and D. The variable defined on line 5 would be the string "5" , and both out-
put statements would use concatenation.
', true, 29);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (142, 'one ', 'The code compiles fine. Line 3 points to the String in the string pool. Line 4
calls the String constructor explicitly and is therefore a different object than s . Lines 5
and 7 check for object equality, which is true, and so print one and three . Line 6 uses
object reference equality, which is not true since we have different objects. Line 7 also
compares references but is true since both references point to the object from the string
pool. Finally, line 8 compares one object from the string pool with one that was explic-
itly constructed and returns false .
', true, 31);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (278, '. The code will not compile because of line 4.	', '. The code compiles and runs without issue, so options C, D, and E are incorrect', false, 51);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (279, '. The code will not compile because of line 5.	', '. The code compiles and runs without issue, so options C, D, and E are incorrect', false, 51);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (280, 'The code will not compile because of line 9. ', '. The code compiles and runs without issue, so options C, D, and E are incorrect', false, 51);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (281, 'Success', '. The value of b
after line 4 is false. However, the if-then statement on line 5 contains an assignment,
not a comparison. The variable b is assigned true on line 3, and the assignment operator
returns true, so line 5 executes and displays Success, so the answer is option A.
', true, 52);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (282, 'Failure', '', false, 52);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (143, 'two', 'The code compiles fine. Line 3 points to the String in the string pool. Line 4
calls the String constructor explicitly and is therefore a different object than s . Lines 5
and 7 check for object equality, which is true, and so print one and three . Line 6 uses
object reference equality, which is not true since we have different objects. Line 7 also
compares references but is true since both references point to the object from the string
pool. Finally, line 8 compares one object from the string pool with one that was explic-
itly constructed and returns false .
', false, 31);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (144, 'three ', 'The code compiles fine. Line 3 points to the String in the string pool. Line 4
calls the String constructor explicitly and is therefore a different object than s . Lines 5
and 7 check for object equality, which is true, and so print one and three . Line 6 uses
object reference equality, which is not true since we have different objects. Line 7 also
compares references but is true since both references point to the object from the string
pool. Finally, line 8 compares one object from the string pool with one that was explic-
itly constructed and returns false .
', true, 31);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (145, 'four  ', 'The code compiles fine. Line 3 points to the String in the string pool. Line 4
calls the String constructor explicitly and is therefore a different object than s . Lines 5
and 7 check for object equality, which is true, and so print one and three . Line 6 uses
object reference equality, which is not true since we have different objects. Line 7 also
compares references but is true since both references point to the object from the string
pool. Finally, line 8 compares one object from the string pool with one that was explic-
itly constructed and returns false .
', true, 31);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (146, 'five', 'The code compiles fine. Line 3 points to the String in the string pool. Line 4
calls the String constructor explicitly and is therefore a different object than s . Lines 5
and 7 check for object equality, which is true, and so print one and three . Line 6 uses
object reference equality, which is not true since we have different objects. Line 7 also
compares references but is true since both references point to the object from the string
pool. Finally, line 8 compares one object from the string pool with one that was explic-
itly constructed and returns false .
', false, 31);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (147, 'The code does not compile.
', 'The code compiles fine. Line 3 points to the String in the string pool. Line 4
calls the String constructor explicitly and is therefore a different object than s . Lines 5
and 7 check for object equality, which is true, and so print one and three . Line 6 uses
object reference equality, which is not true since we have different objects. Line 7 also
compares references but is true since both references point to the object from the string
pool. Finally, line 8 compares one object from the string pool with one that was explic-
itly constructed and returns false .
', false, 31);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (167, '12 ', '', false, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (168, '1234						', '', false, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (169, '1235						', '', false, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (170, '124						', '', false, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (171, '1245', '', false, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (174, '1', '', false, 34);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (175, '2', '', false, 34);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (176, '12', '', false, 34);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (177, 'No output is printed', '', false, 34);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (178, 'An exception is thrown', '', false, 34);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (179, 'The code does not compile', '. The question is trying to distract you into paying attention to logical equality versus
object reference equality. It is hoping you will miss the fact that line 4 does not com-
pile. Java does not allow you to compare String and StringBuilder using == .
', true, 34);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (180, 'An immutable object can be modified.', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', false, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (181, 'An immutable object cannot be modified', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', true, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (182, ' An immutable object can be garbage collected. ', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', true, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (183, 'An immutable object cannot be garbage collected.
', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', false, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (184, 'String is immutable. ', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', true, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (185, 'StringBuffer is immutable.
', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', false, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (186, 'StringBuilder is immutable.', '. Immutable means the state of an object cannot change once it is created.
Immutable objects can be garbage collected just like mutable objects. String is immu-
table. StringBuilder can be mutated with methods like append() . Although
StringBuffer isnâ€™t on the exam, you should know about it anyway in case older ques-
tions havenâ€™t been removed.
', false, 32);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (275, 'The code will not compile because of line 9.', '. The issue with line 9 is
that layEggs() is marked as final in the superclass Reptile, which means it cannot be
overridden', true, 50);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (276, 'Bird is flying', 'The trick here is that the method fly() is marked as private in the parent class Bird,
which means it may only be hidden, not overridden. With hidden methods, the specific
method used depends on where it is referenced. Since it is referenced within the Bird
class, the method declared on line 2 was used, and option A is correct. Alternatively,
if the method was referenced within the Pelican class, or if the method in the parent
class was marked as protected and overridden in the subclass, then the method on line
9 would have been used.', true, 51);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (172, 'The code does not compile', 'The parseName method is invoked within main() on a new Dog object. Line 4 prints
1. The try block executes and 2 is printed. Line 7 throws a NumberFormatException, so
line 8 doesn’t execute. The exception is caught on line 9, and line 10 prints 4. Because the
exception is handled, execution resumes normally. parseName runs to completion, and
line 17 executes, printing 5. That’s the end of the program, so the output is 1245.', true, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (173, 'An uncaught exception is thrown', '', false, 35);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (187, 'Line 4 generates a compiler error', 'Option A (line 4) compiles because short is an integral type.', false, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (188, 'Line 5 generates a compiler error', 'Option B (line
5) generates a compiler error because int is an integral type, but 5.6 is a floating-point
type.', true, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (189, 'Line 6 generates a compiler error.', 'Option C (line 6) compiles because it is assigned a String ', false, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (190, 'Line 7 generates a compiler error', 'Options D and E (lines
7 and 8) do not compile because short and int are primitives. Primitives do not allow
methods to
', true, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (191, 'Line 8 generates a compiler error', 'Options D and E (lines
7 and 8) do not compile because short and int are primitives. Primitives do not allow
methods to
', true, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (192, 'Line 9 generates a compiler error', '', false, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (193, 'The code compiles as is.', '', false, 26);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (194, 'It will not compile.', '', false, 36);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (195, 'It will not run.', '', false, 36);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (196, 'It will run and throw an ArithmeticException.', 'The compiler tests the operation for a valid type but not a valid result, so the code
will still compile and run. At runtime, evaluation of the parameter takes place before
passing it to the print() method, so an ArithmeticException object is raised.
', true, 36);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (197, 'It will run and throw an IllegalArgumentException.						', '', false, 36);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (198, 'None of the above.', '', false, 36);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (199, 'Runtime exceptions are the same thing as checked exceptions.', '', false, 37);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (200, 'Runtime exceptions are the same thing as unchecked exceptions.', 'Runtime exceptions are also known as unchecked exceptions. They are allowed
to be declared, but they don’t have to be. Checked exceptions must be handled or
declared. Legally, you can handle java.lang.Error subclasses, but it’s not a good idea.', true, 37);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (201, ' You can declare only checked exceptions.', '', false, 37);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (202, ' You can declare only unchecked exceptions.', '', false, 37);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (203, 'You can handle only Exception subclasses.', '', false, 37);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (204, 'ArrayIndexOutOfBoundsException', 'java.io.IOException is thrown by many methods in the java.io package,
but it is always thrown programmatically. The same is true for NumberFormatException;
it is thrown programmatically by the wrapper classes of java.lang. The other
three exceptions are all thrown by the JVM when the corresponding problem arises.', true, 38);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (205, ' ExceptionInInitializerError', 'java.io.IOException is thrown by many methods in the java.io package,
but it is always thrown programmatically. The same is true for NumberFormatException;
it is thrown programmatically by the wrapper classes of java.lang. The other
three exceptions are all thrown by the JVM when the corresponding problem arises.', true, 38);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (206, 'java.io.IOException', '', false, 38);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (207, ' NullPointerException	', 'java.io.IOException is thrown by many methods in the java.io package,
but it is always thrown programmatically. The same is true for NumberFormatException;
it is thrown programmatically by the wrapper classes of java.lang. The other
three exceptions are all thrown by the JVM when the corresponding problem arises.', true, 38);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (208, ' NumberFormatException', '', false, 38);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (209, 'Checked exceptions are allowed to be handled or declared.', '', true, 39);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (210, 'Checked exceptions are required to be handled or declared.', '', true, 39);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (211, ' Errors are allowed to be handled or declared.', '', true, 39);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (212, 'Errors are required to be handled or declared.', '', false, 39);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (213, 'Runtime exceptions are allowed to be handled or declared.', '', true, 39);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (214, ' Runtime exceptions are required to be handled or declared.', '', false, 39);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (215, 'abbaaccc ', '', false, 33);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (216, 'abbaccca ', 'This example uses method chaining. After the call to append() , sb contains "aaa" .
That result is passed to the first insert() call, which inserts at index 1. At this point
sb contains abbbaa . That result is passed to the final insert() , which inserts at index
4, resulting in abbaccca .
', true, 33);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (217, 'bbaaaccc', '', false, 33);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (218, 'bbaaccca', '', false, 33);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (219, 'An exception is thrown.', '', false, 33);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (220, 'The code does not compile.
', '', false, 33);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (233, 'final static void method4() { } 
', 'Options A and D are correct because the optional specifiers are allowed in any
order. ', true, 43);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (234, 'public final int void method() { }', 'Options B and C are incorrect because they each have two return types. ', false, 43);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (235, 'private void int method() { }', 'Options B and C are incorrect because they each have two return types. ', false, 43);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (236, 'static final void method3() { } ', 'Options A and D are correct because the optional specifiers are allowed in any
order. ', true, 43);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (237, 'void final method() {}
', 'Options
E and F are incorrect because the return type is before the optional specifier and access
modifier, respectively.
', false, 43);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (238, 'void public method() { }
', 'Options
E and F are incorrect because the return type is before the optional specifier and access
modifier, respectively.
', false, 43);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (239, 'public void methodA() { return;} ', 'Options A and C are correct because a void method is allowed to have a
return statement as long as it doesnâ€™t try to return a value. ', true, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (277, 'Pelican is flying	', '', false, 51);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (240, 'public void methodB() { return null;}', 'Options B and G do not
compile because null requires a reference object as the return type. void is not a refer-
ence object since it is a marker for no return type. int is not a reference object since it is a primitive. 
', false, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (241, 'public void methodD() {}  ', 'Options A and C are correct because a void method is allowed to have a
return statement as long as it doesnâ€™t try to return a value. ', true, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (242, 'public int methodD() { return 9;} ', '. Option D is correct because it returns an int value.', true, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (243, 'public int methodE() { return 9.0;}', 'Option E does not
compile because it tries to return a double when the return type is int . Since a double
cannot be assigned to an int , it cannot be returned as one either.', false, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (244, 'public int methodF() { return;}', 'Option F does not
compile because no value is actually returned.
', false, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (245, 'public int methodG() {return null;}
', 'Options B and G do not
compile because null requires a reference object as the return type. void is not a refer-
ence object since it is a marker for no return type. int is not a reference object since it is a primitive. 
', false, 44);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (246, 'howMany();', '. Option A does
not compile because it does not pass the initial parameter. ', false, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (247, 'howMany(true);', 'Option B creates a vararg array of size 0 and option C creates a vararg array of
size 1.
', false, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (248, ' howMany(true, true);', '', false, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (249, 'howMany(true, true, true);  ', 'Option D passes the initial parameter plus two more to turn into a vararg array
of size 2. ', true, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (250, 'howMany(true, {true});', 'Options E and F do not
compile because they do not declare an array properly. ', false, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (251, 'howMany(true, {true, true});', 'Options E and F do not
compile because they do not declare an array properly. ', false, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (252, 'howMany(true, new boolean[2]); ', 'Option G passes the initial parameter plus an array of size 2. ', true, 46);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (253, 'protected', 'All interface methods are implicitly public, so option B is correct and option A is
not.', false, 47);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (254, 'public', '', true, 47);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (255, 'static', '. Interface methods may be declared as static or default but are never implicitly
added, so options C and F are incorrect', false, 47);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (256, 'void', 'Option D is incorrectâ€”void is not a modifier;
it is a return type. ', false, 47);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (257, 'abstract', 'Option E is a tricky one, because prior to Java 8 all interface methods
would be assumed to be abstract. Since Java 8 now includes default and static
methods and they are never abstract, you cannot assume the abstract modifier will be
implicitly applied to all methods by the compiler
', false, 47);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (258, 'default', '. Interface methods may be declared as static or default but are never implicitly
added, so options C and F are incorrect', false, 47);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (259, '. A reference to an object may be cast to a subclass of the object without an explicit cast.	', '', false, 48);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (260, 'If a method takes a superclass of three objects, then any of those classes may be passed	 as a parameter to the method.	', 'A reference to an object requires an explicit cast if referenced with a subclass,
so option A is incorrect. If the cast is to a superclass reference, then an explicit cast is
not required. Because of polymorphic parameters, if a method takes the superclass of
an object as a parameter, then any subclass references may be used without a cast, so
option B is correct. All objects extend java.lang.Object, so if a method takes that
type, any valid object, including null, may be passed; therefore, option C is correct.
Some cast exceptions can be detected as errors at compile-time, but others can only be
detected at runtime, so D is incorrect. Due to the nature of polymorphism, a public
instance method can be overridden in a subclass and calls to it will be replaced even in
the superclass it was defined, so E is incorrect.
', true, 48);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (261, '	------>
C. A method that takes a parameter with type java.lang.Object will take any reference.	', 'A reference to an object requires an explicit cast if referenced with a subclass,
so option A is incorrect. If the cast is to a superclass reference, then an explicit cast is
not required. Because of polymorphic parameters, if a method takes the superclass of
an object as a parameter, then any subclass references may be used without a cast, so
option B is correct. All objects extend java.lang.Object, so if a method takes that
type, any valid object, including null, may be passed; therefore, option C is correct.
Some cast exceptions can be detected as errors at compile-time, but others can only be
detected at runtime, so D is incorrect. Due to the nature of polymorphism, a public
instance method can be overridden in a subclass and calls to it will be replaced even in
the superclass it was defined, so E is incorrect.
', true, 48);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (262, 'All cast exceptions can be detected at compile-time.', '', false, 48);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (263, 'By defining a public instance method in the superclass, you guarantee that the specific						
method will be called in the parent class at runtime.			', '', false, 48);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (264, 'All methods within them are assumed to be abstract', 'Option A is wrong, because an abstract class may contain concrete methods.
', false, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (265, 'Both can contain public static final variables.', 'Since Java 8, interfaces may also contain concrete methods in form of static or
default methods. Although all variables in interfaces are assumed to be public static
final, abstract classes may contain them as well, so option B is correct.', true, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (266, 'Both can be extended using the extend keyword.		', 'Both abstract
classes and interfaces can be extended with the extends keyword, so option C is correct.
', true, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (267, 'Both can contain default methods.	', '', true, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (268, 'Both can contain static methods.	', 'Only interfaces can contain default methods, so option D is incorrect. Both
abstract classes and interfaces can contain static methods, so option E is correct. ', true, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (269, 'Neither can be instantiated directly.', 'Both
structures require a concrete subclass to be instantiated, so option F is correct.', true, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (270, '. Both inherit java.lang.Object.', 'Finally,
though an instance of an object that implements an interface inherits java.lang.
Object, the interface itself doesnâ€™t; otherwise, Java would support multiple inheritance
for objects, which it doesnâ€™t. Therefore, option G is incorrect.', false, 49);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (271, 'Reptile laying eggs', '. The code doesnâ€™t compile, so options A and B are incorrect. ', false, 50);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (272, 'Lizard laying eggs', '. The code doesnâ€™t compile, so options A and B are incorrect. ', false, 50);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (273, 'The code will not compile because of line 4.	', 'There are no errors on any other lines, so options C and D are incorrect.
', false, 50);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (274, 'The code will not compile because of line 5.	', 'There are no errors on any other lines, so options C and D are incorrect.
', false, 50);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (283, 'The code will not compile because of line 4', 'The code compiles successfully, so options C and D are incorrect', false, 52);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (284, 'The code will not compile because of line 5.', 'The code compiles successfully, so options C and D are incorrect', false, 52);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (285, 'Too Low	', '', false, 53);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (286, 'Just Right', '', false, 53);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (287, 'Too High', '', false, 53);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (288, 'Compiles but throws a NullPointerException.	', '', false, 53);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (289, '. The code will not compile because of line 6.	', 'additional if-then statements, so the correct answer is option F. Option E is
incorrect as Line 6 by itself does not cause a problem, only when it is paired with Line
7. One way to fix this code so it compiles would be to add an if-then statement on
line 6. The other solution would be to remove line 7.
', false, 53);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (290, 'The code will not compile because of line 7		', 'The code does not compile because two else statements cannot be chained together
without additional if-then statements, so the correct answer is option F. ', true, 53);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (291, 'int', 'The value x + y is automatically promoted to int, so int and data types that
can be promoted automatically from int will work. Options A, B, D are such data
types', true, 54);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (292, 'long', 'The value x + y is automatically promoted to int, so int and data types that
can be promoted automatically from int will work. Options A, B, D are such data
types', true, 54);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (293, 'boolean', 'Option C will not work because boolean is not a numeric data type. ', false, 54);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (294, 'double', 'The value x + y is automatically promoted to int, so int and data types that
can be promoted automatically from int will work. Options A, B, D are such data
types', true, 54);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (295, 'short', 'Options E
and F will not work without an explicit cast to a smaller data type.
', false, 54);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (296, 'byte', 'Options E
and F will not work without an explicit cast to a smaller data type.
', false, 54);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (297, 'No change; it compiles as is.', 'The code will not compile as is, so option A is not correct. The value 2 * x
is automatically promoted to long and cannot be automatically stored in y, which is
in an int value.', false, 55);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (298, 'Cast x on line 4 to int.', 'Options B, C, and D solve this problem by reducing the long value to
int.', true, 55);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (299, 'Change the data type of x on line 3 to short.		', 'Options B, C, and D solve this problem by reducing the long value to
int.', true, 55);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (300, 'Cast 2 * x on line 4 to int.	', 'Options B, C, and D solve this problem by reducing the long value to
int.', true, 55);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (301, 'Change the data type of y on line 4 to short.', 'Option E does not solve the problem and actually makes it worse by attempting
to place the value in a smaller data type. ', false, 55);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (302, 'Change the data type of y on line 4 to long.	', 'Option F solves the problem by increasing the
data type of the assignment so that long is allowed.
', true, 55);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (303, 'great', '', false, 56);
INSERT INTO public.answer (id, answer, description, is_correct, question_id) VALUES (304, 'greatgood', '. The
value of grade is ''B'' and there is a matching case statement that will cause "great" to
be printed. There is no break statement after the case, though, so the next case statement
will be reached, and "good" will be printed. There is a break after this case statement,
though, so the switch statement will end. The correct answer is thus option B.', true, 56);
INSERT INTO public.users (id, activation_code, active, emails, first_name, nickname, passwords, last_name) VALUES (63, null, true, 'yeghiazaryan99@gmail.com', 'Aram', 'aaa', '$2a$08$5KMFFxYsJ21FlL01wiaGVe9GD2VTrSPTIx8gQf5exDD4yGlqc7rJK', 'Yeghiazaryan');
INSERT INTO public.users (id, activation_code, active, emails, first_name, nickname, passwords, last_name) VALUES (6, null, true, 'nerseshakobyan001@gmail.com', 'Nerses', 'Nerso', '$2a$12$VnMTPei5pgspGIPqugS4ReAww.cB9PbWGGeSrSdqPhKvqR9qpr5oy', 'Hakobyan');
INSERT INTO public.users (id, activation_code, active, emails, first_name, nickname, passwords, last_name) VALUES (4, null, true, 'edgarohanyan@gmail.com', 'Edgar', 'edgarohanyan', '$2a$12$7lfuJ/5O7.J5t2i7S3Qzu..A2nsus747nid2arSY9n5nbmF1IgoOG', 'Ohanyan');
INSERT INTO public.average_score (id, count, score_value, topic_id, user_id) VALUES (46, 1, 4, 13, 63);
INSERT INTO public.average_score (id, count, score_value, topic_id, user_id) VALUES (45, 3, 9, 14, 63);
INSERT INTO public.test (id, description, duration, test_name) VALUES (47, 'This Test Contains questions from java core', 30, 'Java Core Test');
INSERT INTO public.test (id, description, duration, test_name) VALUES (48, 'Shuffle Test from Java Core and Syntax ', 1, 'Java Test Shuffle ');
INSERT INTO public.test (id, description, duration, test_name) VALUES (49, 'This Test Contains Whole Java Course ', 90, 'Java Big Test');
INSERT INTO public.history (id, end_time, score, session_id, start_time, status, test_id, user_id) VALUES (37, '2019-02-22 02:03:52.679000', 0, null, '2019-02-22 02:03:44.411000', 'COMPLETED', 47, 63);
INSERT INTO public.history (id, end_time, score, session_id, start_time, status, test_id, user_id) VALUES (38, '2019-02-22 12:33:44.839000', 4, null, '2019-02-22 12:32:44.585000', 'COMPLETED', 48, 63);
INSERT INTO public.history (id, end_time, score, session_id, start_time, status, test_id, user_id) VALUES (39, '2019-02-22 12:43:41.094000', 26, null, '2019-02-22 12:36:52.972000', 'COMPLETED', 47, 63);
INSERT INTO public.question_test (question_id, test_id) VALUES (36, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (37, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (38, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (39, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (28, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (29, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (30, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (41, 47);
INSERT INTO public.question_test (question_id, test_id) VALUES (24, 48);
INSERT INTO public.question_test (question_id, test_id) VALUES (25, 48);
INSERT INTO public.question_test (question_id, test_id) VALUES (26, 48);
INSERT INTO public.question_test (question_id, test_id) VALUES (36, 48);
INSERT INTO public.question_test (question_id, test_id) VALUES (37, 48);
INSERT INTO public.question_test (question_id, test_id) VALUES (38, 48);
INSERT INTO public.question_test (question_id, test_id) VALUES (36, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (37, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (38, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (39, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (28, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (29, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (30, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (31, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (34, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (35, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (32, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (33, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (41, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (43, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (44, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (46, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (47, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (48, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (49, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (50, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (51, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (24, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (25, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (26, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (27, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (52, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (53, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (54, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (55, 49);
INSERT INTO public.question_test (question_id, test_id) VALUES (56, 49);

INSERT INTO public.user_role (user_id, roles) VALUES (6, 'USER');
INSERT INTO public.user_role (user_id, roles) VALUES (63, 'ADMIN');
INSERT INTO public.user_role (user_id, roles) VALUES (4, 'ADMIN');


