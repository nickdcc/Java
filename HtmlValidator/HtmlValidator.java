//nick DeChant
//Program analyses a queue of tags and will
//verify if the tags are in a valid sequence or not.
//User also is allowed to add tags to the queue, remove
//tags, and print the current tags in queue. 


import java.util.*;
public class HtmlValidator {

    //fields
    private Queue < HtmlTag > q;
    private int size;

    //constructs an empty queue
    public HtmlValidator() {
        this.q = new LinkedList < HtmlTag > ();
        this.size = 0;
    }

    //constructs a queue with the tags that were
    //called in main
    public HtmlValidator(Queue <HtmlTag> tags) {
        if (tags == null) {
            throw new IllegalArgumentException("Can't pass null to queue.");
            }
            
            Queue <HtmlTag> copy = tags; 
            this.q = copy;
            this.size = q.size();
        }

        //adds a tag to the end of the queue --> front [first, second,third, ...] back
    //procondition: HtmlTag != null
    //postconiditon: size > 0
    public void addTag(HtmlTag tag) {
            if (tag == null) {
                throw new IllegalArgumentException("Can't add an tag of type null.");
                }
                q.add(tag); //add a tag to the end of the line
                size++;
            }

            //returns the current tags in the queue
            public Queue < HtmlTag > getTags() {
                return q;
            }

            //removes all tags of the same element
            //precondition: size > 0  && element != null
            public void removeAll(String element) { //takes in a string
                if (element == null) {
                    throw new IllegalArgumentException("Can't remove a tag of type null.");
                    }

                    int size = q.size();
                    for (int i = 0; i < size; i++) { //[cat,cat,dog,rat,mouse]

                        String peek = q.peek().getElement(); //removes first element & stores it into a string
                        if (!peek.equals(element)) { //if the first element (s) is not the one we want to remove
                            q.add(q.remove());
                            //then re-add it to the end of the list
                        } else {
                            q.remove();
                        }
                    }
                }

                //exmaines queue of tags and will increase indentation
                //for each opening tag, decrease for closing tags,
                //and spot any errors.
                //preconiditon: size > 0
                public void validate() {
                    Stack < HtmlTag > s = new Stack < HtmlTag > ();
                    int size = q.size();
                    int indent = -1;

                    for (int i = 0; i < size; i++) {
                        HtmlTag t = q.peek();
                        if (t.isOpenTag() && !t.isSelfClosing()) { //opening tag that requries closing
                            s.push(q.remove());
                            indent++;
                            indentor(indent);
                            System.out.println(t.toString());
                        } else if (!t.isOpenTag()) { //else closing tag
                            q.remove(t);
                            if (!s.isEmpty()) {
                                HtmlTag stacktop = s.peek();
                                if (t.matches(stacktop)) {
                                    s.pop();
                                    indentor(indent);
                                    indent--;
                                    System.out.println(t.toString());
                                } else { //else its an error
                                    System.out.println("ERROR unexpected tag: " +
                                        t.toString());
                                }
                            } else { //stack is empty so go here
                                System.out.println("ERROR unexpected tag: " +
                                    t.toString());
                            }
                        } else { //else self closing tags
                            q.remove(t);
                            indentor(indent + 1);
                            System.out.println(t.toString());
                        }
                        q.add(t); //re-queue  
                    }

                    while (!s.isEmpty()) { //any tags remaining in stack are errors
                        HtmlTag unclosedTags = s.pop();
                        System.out.println("ERROR unclosed tag: " +
                            unclosedTags.toString());
                    }
                }

                //indentor method
                //precondition: -1 <= i < indent 
                //postcondition: i < indent
                private void indentor(int indent) {
                    for (int i = 0; i < indent; i++) {
                        System.out.print("    ");
                    }
                }
            }