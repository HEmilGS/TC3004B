/* Exercise 0: Test the function by using a spyOn function */

/* Mock the function using spyOn
Write four tests inside a describe block. Do not forget to use the beforeEach function to clear the mock function.
The mock function receives a prop of type number. The mocked function adds a 6 to the number.
1. First Test: 
The forEach function should be called 3 times
2. Second Test:
The forEach function should be called with the correct arguments.
3. Third Test:
Test the first argument of the first call to the function was 0 and the result is 6.
Test the first argument of the second call to the function was 1  and the result is 7.
4. Forth Test:
Test the first argument of the third call to the function was 4 and the result is not 9.
*/

import { forEach } from './forEach'; 

describe('forEach function tests', () => {
  let mockCallback: jest.Mock<number, [number]>;
  
  beforeEach(() => {
    // Create a mock function that adds 6 to the input
    mockCallback = jest.fn((num: number) => num + 6);
    // Clear all mock information before each test
    jest.clearAllMocks();
  });

  test('should be called 3 times', () => {
    const testArray = [0, 1, 4];
    forEach(testArray, mockCallback);
    expect(mockCallback).toHaveBeenCalledTimes(3);
  });

  test('should be called with correct arguments', () => {
    const testArray = [0, 1, 4];
    forEach(testArray, mockCallback);
    expect(mockCallback).toHaveBeenCalledWith(0);
    expect(mockCallback).toHaveBeenCalledWith(1);
    expect(mockCallback).toHaveBeenCalledWith(4);
  });

  test('should return correct results for first two calls', () => {
    const testArray = [0, 1, 4];
    forEach(testArray, mockCallback);
    
    // Test first call
    expect(mockCallback.mock.calls[0][0]).toBe(0);
    expect(mockCallback.mock.results[0].value).toBe(6);
    
    // Test second call
    expect(mockCallback.mock.calls[1][0]).toBe(1);
    expect(mockCallback.mock.results[1].value).toBe(7);
  });

  test('third call should have argument 4 and result not 9', () => {
    const testArray = [0, 1, 4];
    forEach(testArray, mockCallback);
    
    // Test third call
    expect(mockCallback.mock.calls[2][0]).toBe(4);
    expect(mockCallback.mock.results[2].value).not.toBe(9);
    // The actual result should be 10 (4 + 6)
    expect(mockCallback.mock.results[2].value).toBe(10);
  });
});