/*
 * The MIT License
 *
 * Copyright 2024-2026 Μαρέτσικος Χρῆστος.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author Μαρέτσικος Χρῆστος
 */

package PolytLex_plus;

public class Constructors {
    
    private int id;
    private String word;
    private String source1, source2, source3;
                                


public Constructors(){}

public Constructors(int myI, String myW, String myS1, String myS2, String myS3){
    id=myI;
    word=myW;
    source1=myS1;
    source2=myS2;
    source3=myS3;

}


public int myIndex(){
    return this.id;
    
}

public String myWord(){
    return this.word;
}

public String mySource1(){
    return this.source1;
}

public String mySource2(){
    return this.source2;
}

public String mySource3(){
    return this.source3;
}

public void myIndex(int myI){
    this.id=myI;
    
}

public void myWord(String myW){
   this.word=myW;
}

public void mySource1(String myS1){
   this.source1=myS1;
}

public void mySource2(String myS2){
   this.source2=myS2;
}

public void mySource3(String myS3){
   this.source3=myS3;
}

}
