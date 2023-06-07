package dddeurope.workshops.core;

public class RefactoringViolationException extends RuntimeException {
    public RefactoringViolationException() {
        super("This code should not be called during refactoring. " +
                "The purpose of this exception is to prevent unintended mistakes during the workshop." +
                "Take the necessary steps to separate this dependency from your code.");
    }
}
