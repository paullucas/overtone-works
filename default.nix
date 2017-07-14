# http://anderspapitto.com/posts/2015-11-26-overtone-on-nixos-with-jack-and-pulseaudio.html
let pkgs = import <nixpkgs> {};
    fhs = pkgs.buildFHSUserEnv {
      name = "overtone-works";
      targetPkgs = p: with p; [ avahi libsndfile libjack2 fftwSinglePrec leiningen jack2Full ];
      runScript = "bash";
    };
in pkgs.stdenv.mkDerivation {
  name = "overtone-works-shell";
  nativeBuildInputs = [ fhs ];
  shellHook = "exec overtone-works";
}
