import React from 'react';
import { act } from 'react-dom/test-utils';
import { createRoot } from 'react-dom/client';
import Counter from './Counter';

// Mock the console.error to suppress act warnings
beforeAll(() => {
  jest.spyOn(console, 'error').mockImplementation(() => {});
});

describe('Counter component', () => {
  let container: HTMLDivElement;
  let root: any;

  beforeEach(() => {
    container = document.createElement('div');
    document.body.appendChild(container);
    root = createRoot(container);
  });

  afterEach(() => {
    act(() => {
      root.unmount();
    });
    document.body.removeChild(container);
    container.remove();
    jest.clearAllMocks();
  });

  test('increments and decrements when buttons are clicked', () => {
    // Render component
    act(() => {
      root.render(<Counter />);
    });

    // Get elements
    const message = container.querySelector('div');
    const buttons = container.querySelectorAll('button');
    const [decrementBtn, incrementBtn] = buttons; // First button is -, second is +

    // Initial state
    expect(message?.textContent).toBe('Current count: 0-+');

    // Test increment
    act(() => {
      incrementBtn.dispatchEvent(new MouseEvent('click', { bubbles: true }));
    });
    expect(message?.textContent).toBe('Current count: 1-+');

    // Test decrement
    act(() => {
      decrementBtn.dispatchEvent(new MouseEvent('click', { bubbles: true }));
    });
    expect(message?.textContent).toBe('Current count: 0-+');
  });
});