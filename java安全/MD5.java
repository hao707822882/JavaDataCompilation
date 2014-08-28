

    public String encode(final String password) {
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            BigInteger pwInt = new BigInteger(1, md5.digest(password.getBytes()));
            String pwStr = pwInt.toString(16);
            int padding = 32 - pwStr.length();
            StringBuilder sb = new StringBuilder(32);
            for (int i = 0; i < padding; i++) {
                sb.append('0'); // make sure the MD5 password is 32 digits long
            }
            sb.append(pwStr);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CloudRuntimeException("Unable to hash password", e);
        }

    }
}
