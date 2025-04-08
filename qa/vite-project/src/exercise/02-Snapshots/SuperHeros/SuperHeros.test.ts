/* Exercise 2: Test using snapshots */

/* Mock the function using jest.fn().
Write three tests inside a describe block. You should use import the superHeros[] and getFlyingSuperHeros function.

1. First Test: 
Test should return an empty array if no superheros have the 'Fly' power"
2. Second Test:
Test should return an array of superHeros that have the 'Fly' power"
3. Third Test:
Test should match the snapshot of flying superheros.
The snapshot file should contain the expected output of the test.
The snapshot should be saved in a __snapshots__ directory next to the test file.
The snapshot file should be named SuperHeros.test.ts.snap.
*/

import { getFlyingSuperHeros } from './getFlyingSuperHeros';
import {superHeros } from './superHeros';

describe('getFlyingSuperHeros', () => {
  // 1. Test should return an empty array if no superheros have the 'Fly' power
  test('returns empty array when no superheros can fly', () => {
    const nonFlyingHeros = [
      { name: 'Hulk', power: ['Super Strength'] },
      { name: 'Batman', power: ['Intelligence'] }
    ];
    
    const result = getFlyingSuperHeros(nonFlyingHeros);
    expect(result).toEqual([]);
  });

  // 2. Test should return an array of superHeros that have the 'Fly' power
  test('returns only superheros with Fly power', () => {
    const result = getFlyingSuperHeros(superHeros);
    
    // Check that all returned heros have 'Fly' in their powers
    result.forEach(hero => {
      expect(hero.power).toContain('Fly');
    });
    
    // Check that we got the expected number of flying heros
    expect(result).toHaveLength(4);
  });

  // 3. Test should match the snapshot of flying superheros
  test('matches flying superheros snapshot', () => {
    const result = getFlyingSuperHeros(superHeros);
    expect(result).toMatchSnapshot();
  });
});