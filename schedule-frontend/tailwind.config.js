const colors = require('tailwindcss/colors');

module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        black: colors.black,
        white: colors.white,
        gray: colors.gray,
        indigo: colors.indigo,
        red: colors.red,
        yellow: colors.yellow,
        green: colors.green,
        pink: colors.pink,
        purple: colors.purple,
        primary: {
          100: '#13c1ec',
          200: '#11add4',
          300: '#0f9abd',
          400: '#0d87a5',
          500: '#0d7f9b',
          600: '#0b748e',
          700: '#0a6076',
          800: '#084d5e',
          900: '#063a47',
        },
        secondary: {
          100: '#ffe666',
          200: '#ffe14d',
          300: '#ffdd33',
          400: '#ffd91a',
          500: '#ffd500',
          600: '#e6bf00',
          700: '#ccaa00',
          800: '#b39500',
          900: '#998000',
        },
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
