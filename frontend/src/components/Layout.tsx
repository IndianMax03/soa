import { Header } from './Header';

type Props = {
  children: JSX.Element | JSX.Element[];
};

export const Layout = ({ children }: Props) => {
  return (
    <div style={{ display: 'grid', justifyItems: 'center' }}>
      <Header />
      {children}
    </div>
  );
};
