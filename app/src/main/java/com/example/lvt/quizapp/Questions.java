package com.example.lvt.quizapp;

public class Questions {

    public String questions[]={
            " 19 + ……. = 42",
            "What is the symbol of pi",
            "Arrange the numbers in ascending order: 36, 12, 29, 21, 7.",
            "What is the greatest two digit number?",
            "How much is 90 – 19?",
            "20 is divisible by ……… .",
            "Find the value of x; if x = (2 × 3) + 11.",
            "What is the smallest three digit number?",
            "How much is 190 – 87 + 16?",
            "What is 1000 × 1 equal to?",
            "In 24,673 ; the place-value of 6 is ….. .",
            "In 24,673 ; the face-value of 4 is ….. .",
            "The least number of two digits is ….. .",
            "The largest number of six digits is ….. .",
            "The smallest number of seven digits is ….. .",
            "The difference between the smallest number of four digits and the largest number of three digits is ……. .",
            "The sum of the least number of three digits and largest number of two digits is ….. .",
            "If a number has an even number or zero at its unit place; the number is always divisible by ……. .",
            "A number is divisible by 3 if the sum of its digits is divisible by …….. .",
            "A number is divisible by 5 if its unit digit is ………………… ."

    };

    public String choices[][]={
            {"23","61","0","42"},
            {"€","π","Ω","∞"},
            {"36,29,21,12,7","36,29,7,21,12","7,12,21,29,36","None of these"},
            {"10","90","11","99"},
            {"71","109","89","None of the these"},
            {"1","3","7","None of those"},
            {"55","192","17","66"},
            {"100","999","111","101"},
            {"103","261","87","119"},
            {"1","1000","0","None of these"},
            {"700","600","10","100"},
            {"4","2","1000","9999"},
            {"99","11","90","None of these"},
            {"9,99,999","1,00,000","1,11,111","1,11,000"},
            {"0","99,99,999","10,10,100","10,00,000"},
            {"1","100","0","999"},
            {"101","199","111","100"},
            {"2","5","3","7"},
            {"1","2","3","5"},
            {"2 or 0","10 or 0","0 or 5","None of these"}
    };

    public String answers[]={
            "23",
            "π",
            "7,12,21,29,36",
            "99",
            "71",
            "1",
            "17",
            "100",
            "119",
            "1000",
            "600",
            "4",
            "None of these",
            "9,99,999",
            "10,00,000",
            "1",
            "199",
            "2",
            "3",
            "0 or 5"
    };

    public String getQuestion(int a){
        String question=questions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice1=choices[a][0];
        return choice1;
    }

    public String getChoice2(int a){
        String choice2=choices[a][1];
        return choice2;
    }

    public String getChoice3(int a){
        String choice3=choices[a][2];
        return choice3;
    }

    public String getChoice4(int a){
        String choice4=choices[a][3];
        return choice4;
    }

    public String getAnswer(int a){
        String answer=answers[a];
        return answer;
    }
}
