/* Mock Modules */

/* Mock the function using jest.mock() and jest.fn().
Write four tests inside a describe block. Do not forget to use the beforeEach function to clear the mock function.
Mock the functions in the utils files, they are being used in the convertCase function.
Set up the mock functions before each test.

1. Test cases for the reverseString function
2. Test cases for the toLowerCase function
3. Test cases for the toUpperCase function
4. Test cases for the default case when it is unknown
5. Test cases for the empty string
*/
import { convertCase, CaseType } from './convertCase';
import { reverseString, toLowerCase, toUpperCase } from './utils';

// Mock the entire utils module
jest.mock('./utils', () => ({
  reverseString: jest.fn(),
  toLowerCase: jest.fn(),
  toUpperCase: jest.fn(),
}));

describe('convertCase function tests', () => {
  // Clear all mocks before each test
  beforeEach(() => {
    jest.clearAllMocks();
    
    // Set up default mock implementations
    (toUpperCase as jest.Mock).mockImplementation((str) => str.toUpperCase());
    (toLowerCase as jest.Mock).mockImplementation((str) => str.toLowerCase());
    (reverseString as jest.Mock).mockImplementation((str) => 
      str.split('').reverse().join('')
    );
  });

  // 1. Test cases for the reverseString function
  test('should call reverseString when caseType is "reverse"', () => {
    const testString = 'hello';
    const result = convertCase(testString, 'reverse');
    
    expect(reverseString).toHaveBeenCalledTimes(1);
    expect(reverseString).toHaveBeenCalledWith(testString);
    expect(result).toBe('olleh');
  });

  // 2. Test cases for the toLowerCase function
  test('should call toLowerCase when caseType is "lower"', () => {
    const testString = 'HELLO';
    const result = convertCase(testString, 'lower');
    
    expect(toLowerCase).toHaveBeenCalledTimes(1);
    expect(toLowerCase).toHaveBeenCalledWith(testString);
    expect(result).toBe('hello');
  });

  // 3. Test cases for the toUpperCase function
  test('should call toUpperCase when caseType is "upper"', () => {
    const testString = 'hello';
    const result = convertCase(testString, 'upper');
    
    expect(toUpperCase).toHaveBeenCalledTimes(1);
    expect(toUpperCase).toHaveBeenCalledWith(testString);
    expect(result).toBe('HELLO');
  });

  // 4. Test cases for the default case when it is unknown
  test('should return original string when caseType is unknown', () => {
    const testString = 'hello';
    const result = convertCase(testString, 'unknown' as CaseType);
    
    expect(reverseString).not.toHaveBeenCalled();
    expect(toLowerCase).not.toHaveBeenCalled();
    expect(toUpperCase).not.toHaveBeenCalled();
    expect(result).toBe(testString);
  });

  // 5. Test cases for the empty string
  test('should handle empty string correctly', () => {
    const testString = '';
    
    // Test with upper case
    expect(convertCase(testString, 'upper')).toBe('');
    expect(toUpperCase).toHaveBeenCalledWith('');
    
    // Test with lower case
    expect(convertCase(testString, 'lower')).toBe('');
    expect(toLowerCase).toHaveBeenCalledWith('');
    
    // Test with reverse
    expect(convertCase(testString, 'reverse')).toBe('');
    expect(reverseString).toHaveBeenCalledWith('');
    
    // Test with unknown
    expect(convertCase(testString, 'unknown' as CaseType)).toBe('');
  });
});